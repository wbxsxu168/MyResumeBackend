"""
__author__ = "Sun Xu"
__copyright__ = "Copyright 2023, My Resume and Image Processing Demo Project"

"""

from django.shortcuts import (render,get_object_or_404,HttpResponseRedirect)
from django.http import HttpResponse
from django.views.generic.edit import FormView 
from django.urls import reverse_lazy  # new
from django.contrib.auth.mixins import LoginRequiredMixin
from django.contrib.auth.decorators import login_required
from django.contrib.auth.views import LoginView
from django.contrib.auth.forms import AuthenticationForm
from django.views.generic import ListView, CreateView,UpdateView,DeleteView  # new
from .myForms1 import clsUpdateForm,MyAuthForm,PostForm
from .models import MyMFModel1,MyAuditLogModel 
from pathlib import Path
WK_BASE_DIR1 = Path(__file__).resolve().parent.parent
from .ImgProc import (genHistRptImg,genHistEqualizImg,edgeDetectionWColor)  
from django.core.paginator import Paginator
from django.core.paginator import EmptyPage
from django.core.paginator import PageNotAnInteger
from django.db.models import Q # new
from datetime import datetime, timedelta

# pass id attribute from urls
@login_required
def imgProcessingView(request, detail_id):
    context ={}
    context["data"] = MyMFModel1.objects.get(id = detail_id) 
    uuid=context["data"].user.username  if context["data"].user else None 
    uploadImg=context["data"].img
    imgURL=uploadImg.url if uploadImg else None   
#   print ("the detail_id is :", detail_id, "image url::",imgURL)
    ifullpath=WK_BASE_DIR1 / "media" / "images"   
    t1= imgURL[1:]
    fullpath_fn= WK_BASE_DIR1 / t1 	 
#   print('to be processed image dir path:=',ifullpath,"uuid:=",uuid ) 
#   print('to be processed image full dir path +filename : ',fullpath_fn) 
    #genHistRptImg(ifullpath,fullpath_fn,uuid)
    edgeDetectionWColor(ifullpath,fullpath_fn,uuid)
    genHistEqualizImg(ifullpath,fullpath_fn,uuid)       
    return render(request, "myWebApp1/detail_view.html", context)    

# Create your views here.
class HomePageView(LoginRequiredMixin,ListView):
    model = MyMFModel1             
    template_name = "myWebApp1/home.html"
    paginate_by = 3
     
    def get_queryset(self):     # new
        t2 = datetime.today()   # max support latest 90 days for performance concern on DB load
        t1 = t2 - timedelta(days=90)
        sch_query = self.request.GET.get("q") 
        if not sch_query :
            sch_query = ""
        object_list = MyMFModel1.objects.filter(
           Q(create_time__range=[t1, t2]) & Q(user=self.request.user) & ( Q(title__icontains=sch_query) | Q(id__icontains=sch_query) )
        )     
        return object_list

class CreatePostView(LoginRequiredMixin,CreateView):  # new
    model = MyMFModel1
    form_class = PostForm
    template_name = "myWebApp1/post.html"    
    success_url = reverse_lazy("home")  # it is an alias defined in urls.py
    def get_initial(self):
        return {'user': self.request.user}
    
class UpdateRecordView(LoginRequiredMixin,UpdateView):
    model = MyMFModel1
    form_class = clsUpdateForm
    template_name = "myWebApp1/update_view.html"    
    success_url =reverse_lazy("home")    
    def get_initial(self):
        return {'user': self.request.user}

class DelRecordView(LoginRequiredMixin,DeleteView):
    model = MyMFModel1  
    template_name = "myWebApp1/delete_confirm.html"   
    success_url =reverse_lazy("home")   
    def get_initial(self):
        return {'user': self.request.user}
    
class MyLoginView(LoginView):
    authentication_form =MyAuthForm
      
class DownloadPageView(LoginRequiredMixin,ListView):
    model = MyAuditLogModel             
    template_name = "myWebApp1/download.html"
 