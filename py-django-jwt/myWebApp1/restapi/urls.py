from django.urls import include, path
#from . import views
from .views import MyTokenObtainPairView
from .views import ImgRecRestAPIViewSet1
from .views import HtmlResumeAPIView

from rest_framework_simplejwt.views import (
    TokenRefreshView,
)
from rest_framework import routers
from .downldfile import download_file

# define the router
router = routers.DefaultRouter()
# define the router path and viewset to be used
router.register(r'v1', ImgRecRestAPIViewSet1)   #r'api'

from rest_framework import permissions
from drf_yasg.views import get_schema_view
from drf_yasg import openapi

schema_view = get_schema_view(
    openapi.Info(
        title="Swagger First Blog ",
        default_version='v1',
        description="Test Swagger First Blog",
        terms_of_service="https://www.ourapp.com/policies/terms/",
        contact=openapi.Contact(email="wbxsxu168@hotmail.com"),
        license=openapi.License(name="Test Swagger License"),
    ),
    public=True,
    permission_classes=(permissions.AllowAny,),
)


urlpatterns = [
    #this notes is a mimic user data from model notes
    #path('notes/', views.getNotes),
    #http://127.0.0.1:8080/api/token/
    path('token/', MyTokenObtainPairView.as_view(), name='token_obtain_pair'),
    path('token/refresh/', TokenRefreshView.as_view(), name='token_refresh'),
    path('', include(router.urls)),
    path('h1H3Fyw5Kjfy1/', HtmlResumeAPIView.as_view(), name="hmlresapiv"),
    path("fileDwnldT3aVn6SkURtwQ/<int:idx>/", download_file, name ="download_doc"),
    path('swgGrxy6FgcrUnesz/', schema_view.with_ui('swagger', cache_timeout=0),
         name='schema-swagger-ui'),
	path('api-auth/', include('rest_framework.urls'))
]
