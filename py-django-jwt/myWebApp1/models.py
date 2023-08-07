"""
__author__ = "Sun Xu"
__copyright__ = "Copyright 2023, My Resume and Image Processing Demo Project"

"""
from django.db import models  
from django.contrib.auth.models import User

# declare a new model with a name "MyMFModel1"
class MyMFModel1(models.Model):
	# fields of the model
	user = models.ForeignKey(User, on_delete=models.CASCADE,default=1) # null=True)
	title = models.CharField(max_length = 200)
	description = models.TextField()
	create_time = models.DateTimeField(auto_now_add = True)
	last_modified = models.DateTimeField(auto_now = True)
	img = models.ImageField(upload_to = "images/")  ## blank=True
	previous_title = None

	class Meta:
		ordering = ['-create_time']
 
	def __str__(self):
		return self.title
"""	
	@staticmethod
	def post_save(sender, instance, created, **kwargs):
		if instance.previous_title != instance.title or created:
			last_record = sender.objects.latest('id')
			fnme ="images/origin.jpeg"
			fnme=last_record.img.name  
			mfullpath=WK_BASE_DIR / "media"   	
			ifullpath=WK_BASE_DIR / "media" / "images"   

			fullpath_fn= mfullpath / fnme 	 
			print('to be processed image dir path:=',ifullpath ) 
			print('to be processed image full dir path +filename : ',fullpath_fn) 
			edgeDetectionWColor(ifullpath,fullpath_fn)
			genHistEqualizImg(ifullpath,fullpath_fn)
			#genHistRptImg(ifullpath,fullpath_fn)
      
	@staticmethod
	def remember_state(sender, instance, **kwargs):
		instance.previous_title = instance.title

post_save.connect(MyMFModel1.post_save, sender=MyMFModel1)
post_init.connect(MyMFModel1.remember_state, sender=MyMFModel1)
 
"""

class MyAuditLogModel(models.Model):
	# fields of the model
	user = models.ForeignKey(User, on_delete=models.CASCADE,default=1) # null=True)
	src = models.CharField(max_length = 200)	
	dst = models.CharField(max_length = 200)	
	action_notes = models.CharField(max_length = 300)	
	create_time = models.DateTimeField(auto_now_add = True)
	last_modified = models.DateTimeField(auto_now = True)
 
	class Meta:
		ordering = ['-create_time']
 
	def __str__(self):
		return self.title


  