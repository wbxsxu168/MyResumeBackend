from django.contrib import admin
from .models import (MyMFModel1,MyAuditLogModel)
# Register your models here.
admin.site.register(MyMFModel1)
admin.site.register(MyAuditLogModel)


