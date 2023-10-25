import time
import ECFS_get
import attachment_read
import os

# Ping ECFS and get a) filings and b) attachments
# After getting the files, database the key details of each filing
ECFS_get.get_comments()

# Wait
time.sleep(5)

# Get all files in the files directory and convert them into string.
attachment_read.open_folders_and_documents(os.path.join(os.getcwd(), 'files'))

# Then feed the text to ChatGPT AI
# Get results, vet ChatGPT results
# Database the ChatGPT results