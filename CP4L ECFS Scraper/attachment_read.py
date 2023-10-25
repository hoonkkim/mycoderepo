import pypdf
import os
import docx


# Function to recursively open folders and documents
def open_folders_and_documents(directory):


    for x in os.walk(directory):
        for thing in os.listdir(x[0]):
            print()
            if thing[-3:] == 'pdf':
                print(thing)
                pdf_as_string = pdf_to_string(os.path.join(x[0], thing)).replace('\n', '')
                print(pdf_as_string)

            elif thing[-3:] == 'txt':
                print(thing)
                txt_as_string = open(os.path.join(x[0], thing)).read()
                print(txt_as_string.replace('\n', ''))

            elif thing[-3:] == 'doc':
                print(thing)

            elif thing[-3:] == 'rtf':
                print(thing)

def pdf_files(directory: str) -> list:
    """
    Returns a list of all files ending in '.pdf'
     in a given directory.
    Required argument:
        directory -- A directory presumably containing PDF
         files, e.g., 'documents'.
    Returns:
        an alphabetically-sorted list of all files
         ending in '.pdf' within directory, e.g.,
         ['document_a.pdf', 'document_b.pdf',
          'some_other_document.pdf']
    """
    pdf_list = []
    all_list = os.listdir(directory)

    for filename in all_list:
        # print(filename)
        if filename[-4:] == '.pdf':
            pdf_list.append(filename)
    pdf_list.sort()

    return pdf_list


def pdf_to_string(file_location: str) -> str:
    """
    Converts a PDF, including a multi-page PDF,
     into a string, using the pypdf library.
    Required argument:
        file_location -- The location of the PDF file to be
         converted, e.g., 'important_doc.pdf' or
         'some_folder/important_doc.pdf'
    Return:
        a string containing the text content of the
         PDF, including all pages
    """
    pdf_reader = pypdf.PdfReader(file_location)

    pdf_as_string = ''

    for page in pdf_reader.pages:
        pdf_as_string = pdf_as_string + page.extract_text()

    return pdf_as_string

