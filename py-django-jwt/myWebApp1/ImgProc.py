"""
__author__ = "Sun Xu"
__copyright__ = "Copyright 2023, My Resume and Image Process Demo Project"
"""
# importing required libraries of opencv
import cv2
from matplotlib import pyplot as plt
import numpy as np
from pathlib import Path

def genHistRptImg(path,fn='origin0.jpeg',uuid='anonymous'):
    try:
        # reads an input image
        sfn=str(fn)
        img = cv2.imread(sfn,0)

        # finding the frequency of pixels in range 0-255
        histr = cv2.calcHist([img],[0],None,[256],[0,256])

        plt.switch_backend('Agg') 
        plt.xlabel('Pixel gray level')
        plt.ylabel('Its happing frequency')
        plt.plot(histr)
        d1=uuid+"_"+"result_histogram.png"
        plt.savefig(str(path / d1))  #src="media/images/uuid_result_histogram.png">
        plt.show()
        plt.close()
        
    except IOError:
        print ('Error while reading the image files !!!')
    
    

def genHistEqualizImg(path,fn='origin0.jpeg',uuid='anonymous'):
    try:    
        # reading an image using imread
        sfn=str(fn)
        ori_img = cv2.imread(sfn, 0)       
        equ_img = cv2.equalizeHist(ori_img)  
           
        z1=uuid+"_"+"histequ_result.jpeg"
        cv2.imwrite(str(path / z1 ),equ_img) 
          
      # find the frequency of pixels in range 0-255
        histr0 = cv2.calcHist([ori_img],[0],None,[256],[0,256])
        plt.switch_backend('Agg') 
        plt.xlabel('Pixel gray level')
        plt.ylabel('Frequency')
        
        plt.plot(histr0)
        d1=uuid+"_"+"hist_rpt_curve0.png"
        plt.savefig(str(path / d1))  #src="media/images/uuid_hist_rpt_curve0.png">
        plt.show()
  
         
        histr1 = cv2.calcHist([equ_img],[0],None,[256],[0,256])        
        plt.plot(histr1)
        d2=uuid+"_"+"hist_rpt_curve1.png"
        plt.savefig(str(path / d2))  #src="media/images/uuid_hist_rpt_curve1.png">
        plt.show()
        
        plt.close()        
            
    except IOError:
        print ('Error while reading the image files !!!')
    
def edgeDetectionWColor(path,fn='origin0.jpeg',uuid='anonymous'):
    #FILE_NAME = 'retinal_diab1.jpeg'
    try:
        # Read image from disk.
        sfn=str(fn)
#       print("str(fn)=",sfn)
        image1 = cv2.imread(sfn)
        d1=uuid+"_"+"origin.jpeg"
        cv2.imwrite(str(path / d1),image1)   #make a copy for origin image

        # Leverage Canny edge detection.
        edgeimg = cv2.Canny(image1, 100, 200)

        # Write image back to disk.
        d2=uuid+"_"+"pure_edge.jpg"

        puredgefn=str(path / d2 )
#       print("puredgefn=",puredgefn)
        
        cv2.imwrite(puredgefn, edgeimg)   #pure edge image
    
        image2 = cv2.imread(puredgefn )
        # cv2.addWeighted is applied over the
        # image inputs with applied parameters
        image3 = cv2.addWeighted(image1, 0.5, image2, 0.4, 0)

        # the window showing output image
        # with the weighted sum
        #cv2.imshow('Edge detection on retinal vasucular image', weightedSum)
        d3=uuid+"_"+"ori_edge_combined_result.jpeg"
        cv2.imwrite(str(path / d3 ),image3)
        
        mask = np.ones((5, 5), np.uint8)           
        img_dilation = cv2.dilate(edgeimg, mask, iterations=1)
        d4=uuid+"_"+"dilation.jpeg"
        cv2.imwrite(str(path / d4 ), img_dilation)

        img_closing = cv2.erode(img_dilation, mask, iterations=1)
        d5=uuid+"_"+"closing.jpeg"
        cv2.imwrite(str(path / d5 ), img_closing)
 
    except IOError:
        print ('Error while reading the image files !!!')
