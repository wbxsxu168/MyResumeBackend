# import serializer from rest_framework
from rest_framework import serializers

# import model from models.py
from .. models import MyMFModel1

# Create a model serializer
class GAPIImgRecSerializer(serializers.HyperlinkedModelSerializer):
	img = serializers.ImageField(
            max_length=None, use_url=True,read_only=True
        ) 
	owner_nme = serializers.SerializerMethodField('get_owner_name')
	create_time = serializers.DateTimeField(read_only=True)
	last_modified = serializers.DateTimeField(read_only=True)
  
	# specify model and fields
	class Meta:
		model = MyMFModel1
		fields = ('id','img','owner_nme','title','description','create_time','last_modified')
        
	def get_owner_name(self, obj):
		return obj.user.username
  
  
 