import requests
from datetime import date, timedelta, datetime
from selenium import webdriver
from selenium.webdriver.common.by import By
import time
import os
import database

def get_comments() -> None: #api_key, docket_number):

    APIURL = "https://publicapi.fcc.gov/ecfs/"

    try:  # check for  api key
        api_key = 'BxepYUkiAaqNXfwGzPNNbIypRSnMeH5N0OzVLuGx'
    except (ValueError, IndexError):
        raise ValueError("API key is not supplied  ")

    # this will eventually be user input
    APIKey = "&api_key=" + api_key
    # this will evenutually be user input
    docket_number = '17-108'

    # make a list of start dates and end dates with parameters
    datetime_list = []

    start_date_string = "2017-06-02T00:00:00.000Z"
    end_date_string = "2017-06-02T02:00:00.000Z"
    format_string = "%Y-%m-%dT%H:%M:%S.%fZ"

    start_datetime = datetime.strptime(start_date_string, format_string)
    end_datetime = datetime.strptime(end_date_string, format_string)

    datetime_cursor_start = start_datetime

    while datetime_cursor_start < end_datetime:
        datetime_cursor_end = datetime_cursor_start + timedelta(hours=1)

        str_cursor_start = datetime.strftime(datetime_cursor_start, "%Y-%m-%dT%H:%M:%S.%fZ")[:-4] + 'Z'
        str_cursor_end = datetime.strftime(datetime_cursor_end, "%Y-%m-%dT%H:%M:%S.%fZ")[:-4] + 'Z'

        datetime_list.append((str_cursor_start, str_cursor_end))
        datetime_cursor_start += timedelta(hours=1)
    print("start time: "+datetime_list[0][0])
    print("end time: " + datetime_list[-1][1])

    # loop through dates and inject into API url to pull all filings for range
    url_list = []

    # Need to figure out how to slice by TIME because there are 1000+ comments per day...
    for i in datetime_list:
        url = APIURL + 'filings?' + APIKey \
              + '&date_submission=[gte]' + (i[0]) + '[lt]' + i[1] \
              + '&proceedings.name=' + docket_number
        url_list.append(url)

    # need to incorporate datetime based folder creation and adaptation

    # Every URL will return a response in json format.
    # We convert the json into a dictionary so it's easy to parse.
    filings_dicts = []
    for url in url_list:
        print(url)
        response = requests.get(url)
        parsed = response.json()
        # filings_dicts.append(parsed)
        # hourly list means too many API calls
        # need to process per call

        filings_list = parsed['filing']
        # Probably only want comments, not ex parte meetings? Or just file them differently?
        print(filings_list)
        x = 0
        for filing in filings_list:
            x += 1
            # what are the items that I want to enter into the db?
            # Description of submissiontype -> String/Varchar
            # filing_id, filing_date, submission_type, express, filer_name
            filing_id = filing['id_submission']
            filer_name = filing['filers'][0]['name']
            filing_date = datetime.strptime(filing['date_received'], '%Y-%m-%dT%H:%M:%S.%fZ').date()
            # print(filing_date)
            information_for_db = (filing_id
                                  , filing_date
                                  , filing['submissiontype']['short']
                                  , filing['express_comment']
                                  , filer_name)
            database.insert_filing(information_for_db)

            # slot in a different function for taking the records and logging them into a database
            if filing['express_comment'] == 0:
                doc_urls = []
                for docs in filing['documents']:
                # URL of attachment
                    filetype = docs['filename'][-3:]
                    doc_urls.append([docs['src'], filetype])
                # print(doc_urls)
                get_pdf_from_comment(filing_date, doc_urls, filer_name, filing_id)
            # else:
                # print(filing)
        print("Comment count: "+str(x))
        time.sleep(5)

def get_pdf_from_comment(comment_date, url_list, filer_name, filing_id) -> None:
    # Set the path to the WebDriver executable
    # Update this with the path to your WebDriver
    webdriver_path = '/Users/hoonkim/Documents/chromedriver-mac-arm64/chromedriver.exe'
    comment_date_str = date.strftime(comment_date, "%m-%d-%Y")
    foldername = filer_name + '_'+filing_id
    os.makedirs('files', exist_ok=True)
    os.makedirs(os.path.join('files', comment_date_str, foldername), exist_ok=True)
    pdf_path = os.path.join(os.getcwd(), 'files', comment_date_str, foldername)

    i = 0
    for url in url_list:

        # Initialize the Chrome WebDriver
        # Configure Chrome options
        chrome_options = webdriver.ChromeOptions()
        chrome_options.add_argument("--no-sandbox")  # This is a common security option
        chrome_options.add_argument(f"--disable-extensions")  # Disable browser extensions
        chrome_options.add_argument(f'--disable-infobars')
        chrome_options.add_experimental_option("prefs", {
            "download.default_directory": pdf_path,
            "download.prompt_for_download": False,  # Automatically download files
            "download.directory_upgrade": True,  # Overwrite existing files with the same name
        })

        ##### Can I rename the file to reflect the comment ID?

        # Initialize the Chrome WebDriver with options
        driver = webdriver.Chrome(options=chrome_options)

        # Navigate to the URL where the PDF is located
        driver.get(url[0])

        # Wait for a few seconds to ensure the PDF is loaded (adjust the wait time as needed)
        time.sleep(5)

        # Find the element that represents the link to the PDF
        # This could be an anchor element with a specific text or an element with a specific ID or class
        if url[1] == 'pdf':
            pdf_link = driver.find_element(By.CSS_SELECTOR, 'a')
        # Click the link to download the PDF
            pdf_link.click()

        # Need a way to overwrite pre-existing files
        # Chrome settings?

        # Wait for the download to complete (you may need to adjust this time)
        time.sleep(5)
        # Close the browser
        driver.quit()

        i += 1


