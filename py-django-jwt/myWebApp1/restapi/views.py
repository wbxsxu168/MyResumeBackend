from django.http import JsonResponse
from rest_framework import permissions
from rest_framework.response import Response
from rest_framework.decorators import api_view, permission_classes
from rest_framework.permissions import IsAuthenticated
from rest_framework_simplejwt.serializers import TokenObtainPairSerializer
from rest_framework_simplejwt.views import TokenObtainPairView
from rest_framework import viewsets
from .serializer import GAPIImgRecSerializer
from .. models import MyMFModel1
from rest_framework.renderers import TemplateHTMLRenderer
from rest_framework.views import APIView
from django.template.defaulttags import register
from django.conf import settings

class MyTokenObtainPairSerializer(TokenObtainPairSerializer):
    @classmethod
    def get_token(cls, user):
        token = super().get_token(user)
        # Add custom claims
        token['username'] = user.username
        # ...
        return token

class MyTokenObtainPairView(TokenObtainPairView):
    serializer_class = MyTokenObtainPairSerializer

class ImgRecRestAPIViewSet1(viewsets.ModelViewSet):
	# define queryset
	queryset = MyMFModel1.objects.all()
	# specify serializer to be used
	serializer_class = GAPIImgRecSerializer 
	search_fields = ("id","title", )
	ordering_fields = ("id",)
	ordering = ("-id", )
 
	def get_permissions(self):
		if self.request.method in ['PUT', 'DELETE',"POST"]:
			return [permissions.IsAdminUser()]
		return [permissions.IsAuthenticated()]

	def get_queryset(self):
		queryset = self.queryset
		if (not self.request.user.is_staff):
			query_set = queryset.filter(user=self.request.user)
			return query_set
		else:
			return  queryset.all()

#===========================HtmlResumeAPIView===================
# React2ResumePage
# Copyright 2023, Sun Xu MyResume Project

class HtmlResumeAPIView(APIView):
    queryset = MyMFModel1.objects.all()
    renderer_classes = [TemplateHTMLRenderer]
    template_name = 'myWebApp1/resume2react.html'

    @register.filter
    def getDictItem(dictionary, key):
        return dictionary.get(key)
    
    def get(self, request):
 #      queryset = self.get_queryset()     
      
        base_path="http://localhost:8080/"
        if (not settings.DEBUG):
            base_path="___your_backend_URL_here___"
        
        bkendServiceURL=base_path    
        prefx_imgauthurl_path=base_path+"media/docs"
        prefx_secimgurl_path=prefx_imgauthurl_path+"/secimg"
        
        prefx_imgurl_path=base_path+"static/sklseticons_r3y19rvGh"
        prefx_public_path=base_path+"static/public_tFy5V1E0Kn"
        
        tou_doc_Lnk=prefx_public_path+"/termofuse.html" 
        ppolicy_doc_Lnk=prefx_public_path+"/ppolicy.html" 

        pub_resume_ico=prefx_public_path+"/pub_resume_icon.png"
        csc_greenbelt=prefx_imgurl_path+"/csc_greenbelt.png" 
        python=prefx_imgurl_path+"/python.png" 
        django=prefx_imgurl_path+"/django.png" 
        django_restfrmwrk=prefx_imgurl_path+"/django-restfrmwrk.png" 
        opencv=prefx_imgurl_path+"/opencv-ico.png" 	
        bootstrap_logo=prefx_imgurl_path+"/bootstrap-logo.png" 
        tcp_ico=prefx_imgurl_path+"/tcp_ico.png" 
        c_logo=prefx_imgurl_path+"/c-logo.png" 
        jira_icon=prefx_imgurl_path+"/jira-icon.png" 
        wireshark_ico=prefx_imgurl_path+"/wireshark_ico.png" 
        CPP_ico=prefx_imgurl_path+"/CPP_ico.png" 
        React_icon=prefx_imgurl_path+"/React-icon.png" 
        j2ee_ico=prefx_imgurl_path+"/j2ee_ico.jpeg" 
        sringboot_ico=prefx_imgurl_path+"/springboot-ico.png" 
        oracle_ico=prefx_imgurl_path+"/oracle-ico.png" 
        postgresql_logo=prefx_imgurl_path+"/postgresql-logo.png" 
        grafana_icon=prefx_imgurl_path+"/grafana-icon.png" 
        tableau_icon=prefx_imgurl_path+"/tableau-icon.png" 
        elk_ico=prefx_imgurl_path+"/elk-ico.png" 
        postman_icon=prefx_imgurl_path+"/postman-icon.png" 
 
        wbx_icon=prefx_imgurl_path+"/wbx_ico.jpeg"       
        jbr_icon=prefx_imgurl_path+"/jabber-logo.png"       
        csc_icon=prefx_imgurl_path+"/csco-logo.png" 
        spie_icon=prefx_imgurl_path+"/spie1.png" 
        zju_icon=prefx_imgurl_path+"/ZJUniversityLogo.png"   
        phd_icon=prefx_imgurl_path+"/PhD.png"

        img_CL=prefx_imgurl_path+"/sunxuCL.png"
        img_RL=prefx_imgurl_path+"/sunxuResume.png"            
        sunx_avatar=prefx_imgurl_path+"/sunxuImg.png"    
        sunxs_kickball=prefx_public_path+"/play_soccer0.png"       
        sfggb_img_Lnk=prefx_imgurl_path+"/sfggb.webp"    
        sunx_doc_RLnk="/myresume/dwnldfile/2/"  
        sunx_doc_CLnk="/myresume/dwnldfile/1/"  
        
        Orgs_set={
        "pubrmico": pub_resume_ico,
        "wbx" : wbx_icon,       
        "jbr" : jbr_icon,  
        "csc" : csc_icon, 
        "spie": spie_icon, 
        "zju" : zju_icon,   
        "phd" : phd_icon,                         
        "coverletter":  img_CL, 
        "resume":       img_RL,   
        "kicksoccball": sunxs_kickball, 
        "myavatar": sunx_avatar, 
        "docCLnk": sunx_doc_CLnk, 
        "docRLnk": sunx_doc_RLnk, 
        "ToUseLnk": tou_doc_Lnk, 
        "PPolicyLnk": ppolicy_doc_Lnk, 
        "sfggbridge": sfggb_img_Lnk,
        "bkendSvrURL":bkendServiceURL,
        }
        
        myskill_set={       
        "csc_greenbelt":csc_greenbelt, 
        "python":python,
        "django": django, 
        "django_restfrmwrk": django_restfrmwrk, 
        "opencv": opencv, 	
        "bootstrap_logo": bootstrap_logo, 
        "tcp_ico": tcp_ico, 
        "c_logo": c_logo, 
        "jira_icon": jira_icon, 
        "wireshark_ico": wireshark_ico, 
        "CPP_ico": CPP_ico,
        "React_icon": React_icon, 
        "j2ee_ico": j2ee_ico,
        "sringboot_ico": sringboot_ico, 
        "oracle_ico":oracle_ico, 
        "postgresql_logo": postgresql_logo, 
        "grafana_icon": grafana_icon, 
        "tableau_icon": tableau_icon, 
        "elk_ico": elk_ico,  
        "postman_icon": postman_icon   
        }

        recogimg_fp1=prefx_imgurl_path+"/sunxuCscAwards.png"
        recogimg_fp2=prefx_imgurl_path+"/sunxuWbxAwards.png"
        
        recogimg_fp3=prefx_secimgurl_path+"/2006_resume.png"
        recogimg_fpc1=prefx_secimgurl_path+"/myrecog/sxu1.png"
        recogimg_fpc2=prefx_secimgurl_path+"/myrecog/sxu2.png"
        recogimg_fpc3=prefx_secimgurl_path+"/myrecog/sxu3.png"
        recogimg_fpc4=prefx_secimgurl_path+"/myrecog/sxu4.png"
        recogimg_fpc5=prefx_secimgurl_path+"/myrecog/sxu5.png"
        recogimg_fpc6=prefx_secimgurl_path+"/myrecog/sxu6.png"
        recogimg_fpc7=prefx_secimgurl_path+"/myrecog/sxu7.png"
        recogimg_fpc8=prefx_secimgurl_path+"/myrecog/sxu8.png"
        recogimg_fpc9=prefx_secimgurl_path+"/myrecog/sxu9.png"
        
        myrecognition_set1={
            "csc_award":recogimg_fp1, 
            "wbxteo_award":recogimg_fp2,
            } 
        
        myrecognition_set={ 
            "csc_award":recogimg_fp1, 
            "wbxteo_award":recogimg_fp2,
            "wbx2006_award":recogimg_fp3,
            "csc_support_award1":recogimg_fpc1, 
            "csc_support_award2":recogimg_fpc2, 
            "csc_support_award3":recogimg_fpc3, 
            "csc_support_award4":recogimg_fpc4, 
            "csc_support_award5":recogimg_fpc5, 
            "csc_support_award6":recogimg_fpc6, 
            "csc_support_award7":recogimg_fpc7, 
            "csc_support_award8":recogimg_fpc8, 
            "csc_support_award9":recogimg_fpc9
        }        
        
        if (not settings.DEBUG): 
            myrecognition_set=myrecognition_set1
               
        slide_idx_set={
            0:"1",
        }
        
        if (settings.DEBUG): 
            for k in range(1, 12):
                slide_idx_set[k]=str(k+1)       
        else:
            for k in range(1, 2):
                slide_idx_set[k]=str(k+1)       

#  language mastering status bar
        sknme2prgstatus={
            "Network":"95",
            "Full Stack":"80",
            "Production Support":"85",
            "SQL":"91",
            "DevOPs":"70",
            "Security":"75",           
        }
        sknme2barcolor={
            "Network":"#800080",
            "Full Stack":"#7FFFD4",
            "Production Support":"#FFC0CB",
            "SQL":"#A52A2A",
            "DevOPs":"#ADD8E6", 
            "Security":"#808000",        
        }       
        sknmedegreeLst = []
        for (k,v1) in sknme2prgstatus.items():
            cpdata1 = {'name':k, 'valueP':v1, 'valueC':sknme2barcolor[k] }
            sknmedegreeLst.append(cpdata1)
        
        return Response({ #'imgrecords': queryset,
                         'skill_set':myskill_set,
                         'recogn_set':myrecognition_set,
                         'dsp_idx_set':slide_idx_set,
                         'skl_degree_lst':sknmedegreeLst ,
                         'orgs_set':Orgs_set,
                         })

    def get_permissions(self):
        if self.request.method in ['PUT', 'DELETE',"POST"]:
            return [permissions.IsAdminUser()]
        return [permissions.IsAuthenticated()]

    def get_queryset(self):
        queryset = self.queryset
        if (not self.request.user.is_staff):
            query_set = queryset.filter(user=self.request.user)
            return query_set
        else:
            return self.queryset.all()