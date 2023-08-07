from django.http import HttpResponse
from rest_framework.decorators import api_view, permission_classes
from rest_framework.permissions import IsAuthenticated
from pathlib import Path

WK_BASE_API_DIR = Path(__file__).resolve().parent.parent.parent
  
@api_view(['GET'])
@permission_classes([IsAuthenticated])
def download_file(request,idx):
    docbase_path=WK_BASE_API_DIR  / "media" / "docs" / "secimg" 
    # Logic to fetch the file and determine its path on the server
    doc_nme=" "
    if idx==0:
        doc_nme="sunxu_cl.docx"
    elif idx==1: 
        doc_nme="sunxu_resume.docx"
    else:
        doc_nme="resume.txt"

    file_path = docbase_path / doc_nme               
    # Read the file content
    with open(file_path, 'rb') as file:
        file_data = file.read()

    # Prepare the HTTP response with appropriate headers
    response = HttpResponse(file_data, content_type='application/vnd.openxmlformats-officedocument.wordprocessingml.document')
    response['Content-Disposition'] = 'attachment; filename='+doc_nme+"'"  # Specify the desired file name and extension
    return response
