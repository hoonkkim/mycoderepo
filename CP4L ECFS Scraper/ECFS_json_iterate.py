import json
import requests
import sys
from datetime import date, timedelta


APIUrl = "https://publicapi.fcc.gov/ecfs/"

try:  #check for  api key
        api_key = 'BxepYUkiAaqNXfwGzPNNbIypRSnMeH5N0OzVLuGx'
except (ValueError, IndexError):
    raise ValueError("API key is not supplied  ")

APIKey = "&api_key="+api_key
docket_number = '17-108'

# make a list of start dates and end dates with parameters
datelist = []
start_date = date(2017, 4, 26)
end_date = date(2017, 6, 29)    # perhaps date.now()
delta = end_date - start_date   # returns timedelta
delta_half = int(delta.days/2)

for i in range(delta_half+1):
    day = start_date + timedelta(2*i)
    nextday = start_date + timedelta(2*i+1)
    day_str = day.strftime('%Y-%m-%d')
    nextday_str = nextday.strftime('%Y-%m-%d')
    datelist.append((day_str, nextday_str))

# loop through dates and inject into API url to pull all filings for range
url_list = []

for i in datelist:
    date0 = i[0]
    date1 = i[1]
    url=APIUrl +'filings?'+APIKey\
        +'&date_received=%5Bgte%5D'+date0+'%5Blte%5D'+date1\
        +'&proceedings.name='+docket_number
    url_list.append(url)

filings_dicts = []
for url in url_list:
    # print(url)
    response = requests.get(url)
    parsed = response.json()
    filings_dicts.append(parsed)

for i in range(len(filings_dicts)):
    filings_list = filings_dicts[i]['filing']
    # Probably only want comments, not ex parte meetings? Or just file them differently?
    for filing in filings_list:
        # what are the items that I want to enter into the db?
        # Description of submissiontype -> String/Varchar
        print(filing['submissiontype']['abbreviation'])
        # date -> Date
        print(filing['date_received'])
        # submission id -> PK
        print(filing['id_submission'])
        # ExpressComment -> Boolean
        print(filing['express_comment'])
        # Filer: name
        print(filing['filers'])
        # whether it has an attachment

        # slot in a different function for taking the records and logging them into a database
        if filing['express_comment'] == 0:
            # print(filing)
            print(filing['documents'])
            for docs in filing['documents']:
            # URL of attachment
                print(filing['documents'][docs]['src'])


    break