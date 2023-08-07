"""
__author__ = "Sun Xu"
__copyright__ = "Copyright 2023, My Resume and IMage Process Demo Project"

"""
from django import forms
from .models import MyMFModel1
from django.contrib.auth.forms import AuthenticationForm   
from django.utils.translation import gettext_lazy as _    
from django.db import models

# create a ModelForm: in use 
class clsUpdateForm(forms.ModelForm):

    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
        self.fields['user'].disabled = True
        self.fields['img'].disabled = True
        
    class Meta:
        model = MyMFModel1
		#fields = "__all__"
    # specify the fields
        fields = [
            "user",
			"title",
			"description",
            "img"
 		]      
 
class PostForm(forms.ModelForm):
    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
        self.fields['user'].disabled = True
        
    class Meta:
        model = MyMFModel1
        fields = ["user","title","description", "img"]

        
class MyAuthForm(AuthenticationForm):
    error_messages = {
        'invalid_login': _(
            "The information you entered does not match our records! Please try again."
            #"You may retry.. Note: Case-sensitive."
        ),
        'inactive': _("This account is deactivated status."),
    }
    
    def confirm_login_allowed(self, user):
        if not user.is_active:
            raise forms.ValidationError(
                self.error_messages['inactive'],
                code='inactive',
            )

    def get_user(self):
        return self.user_cache

    def get_invalid_login_error(self):
        return forms.ValidationError(
            self.error_messages['invalid_login'],
            code='invalid_login',
            params={'username': self.username_field.verbose_name},
        )    
