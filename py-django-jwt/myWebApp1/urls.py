from django.urls import path
from . import views
from .myForms1 import  MyAuthForm 
from django.contrib.auth import views as auth_views
from django.urls import  re_path
from django.contrib.auth.decorators import login_required
from django.views.static import serve
from django.conf import settings

@login_required
def protected_serve(request, path, document_root=None, show_indexes=False):
    return serve(request, path, document_root, show_indexes)

urlpatterns=[
    path('img/<int:detail_id>/', views.imgProcessingView, name="detail"),
    path('', views.HomePageView.as_view(), name="home"),
    path('resumedownld/', views.DownloadPageView.as_view(), name="download"), 
    path("img/postretinalimgjA6kyWKy/", views.CreatePostView.as_view(), name="addrec"),  # new 
    path('img/<pk>/update', views.UpdateRecordView.as_view(), name="updaterec"), 
    path('img/<pk>/delete', views.DelRecordView.as_view(), name="delrec"), 
    path('login/', views.MyLoginView.as_view(),  {'template_name': 'login.html','authentication_form': MyAuthForm}, name="login"),  # new 
    path('logout/', auth_views.LogoutView.as_view(), name="logout"),  # new 
    re_path(r'^%s(?P<path>.*)$' % settings.MEDIA_URL[1:], protected_serve, {'document_root': settings.MEDIA_ROOT})
 #  re_path(r'^static/(?P<path>.*)$', serve,{'document_root': settings.STATIC_ROOT})
 ]
  
 
  
