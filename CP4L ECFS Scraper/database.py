import mysql.connector
import mysql

def insert_filing(information) -> None:

    cnx = mysql.connector.connect(user='root', password='sg8112MW!',
                                  host='localhost',
                                  database='CP4L_MEGA')
    cursor = cnx.cursor()

    # print(information)
    add_filing = ("INSERT INTO Filings "
                    "(filing_id, filing_date, submission_type, express, filer_name) "
                    "VALUES (%s, %s, %s, %s, %s)")
    # Need conditions to check whether filing_id (primary key) already exists
    # If it exists it won't insert (where statement) because attempt to insert will break SQL instance
    cursor.execute(add_filing, information)

    cnx.commit()
    # print('Inserted: ', information)
    cnx.close()

def insert_GPT(GPT_text) -> None:
    print()
    # Get results, vet ChatGPT results
    # Database the ChatGPT results