package routes

import (
	"fmt"
	controller "go-jwt-mg/src/controllers"
	"net/http"

	"github.com/gin-gonic/gin"
)

// UserRoutes
func UserRoutes(route *gin.Engine) {
	route.POST("/api/signup", controller.SignUp())
	route.POST("/api/token/", controller.Login())
}

// ======== Sun Xu added ========
// React2ResumePage
func HomeRoutes(route *gin.Engine) {
	route.GET("/api/h1H3Fyw5Kjfy1/", func(ctx *gin.Context) {
		//---------------var set --------------------------------
		base_path := "http://localhost:8080/"
		var isDebug = true
		if !isDebug {
			base_path = "https://wbxsxu168.pythonanywhere.com/"
		}
		bkendServiceURL := base_path
		prefx_imgauthurl_path := base_path + "media/docs"
		prefx_secimgurl_path := prefx_imgauthurl_path + "/secimg"

		prefx_imgurl_path := base_path + "static/sklseticons_r3y19rvGh"
		prefx_public_path := base_path + "static/public_tFy5V1E0Kn"

		tou_doc_Lnk := prefx_public_path + "/termofuse.html"
		ppolicy_doc_Lnk := prefx_public_path + "/ppolicy.html"

		pub_resume_ico := prefx_public_path + "/pub_resume_icon.png"
		csc_greenbelt := prefx_imgurl_path + "/csc_greenbelt.png"
		python := prefx_imgurl_path + "/python.png"
		django := prefx_imgurl_path + "/django.png"
		django_restfrmwrk := prefx_imgurl_path + "/django-restfrmwrk.png"
		opencv := prefx_imgurl_path + "/opencv-ico.png"
		bootstrap_logo := prefx_imgurl_path + "/bootstrap-logo.png"
		tcp_ico := prefx_imgurl_path + "/tcp_ico.png"
		c_logo := prefx_imgurl_path + "/c-logo.png"
		jira_icon := prefx_imgurl_path + "/jira-icon.png"
		wireshark_ico := prefx_imgurl_path + "/wireshark_ico.png"
		CPP_ico := prefx_imgurl_path + "/CPP_ico.png"
		React_icon := prefx_imgurl_path + "/React-icon.png"
		j2ee_ico := prefx_imgurl_path + "/j2ee_ico.jpeg"
		sringboot_ico := prefx_imgurl_path + "/springboot-ico.png"
		oracle_ico := prefx_imgurl_path + "/oracle-ico.png"
		postgresql_logo := prefx_imgurl_path + "/postgresql-logo.png"
		grafana_icon := prefx_imgurl_path + "/grafana-icon.png"
		tableau_icon := prefx_imgurl_path + "/tableau-icon.png"
		elk_ico := prefx_imgurl_path + "/elk-ico.png"
		postman_icon := prefx_imgurl_path + "/postman-icon.png"

		wbx_icon := prefx_imgurl_path + "/wbx_ico.jpeg"
		jbr_icon := prefx_imgurl_path + "/jabber-logo.png"
		csc_icon := prefx_imgurl_path + "/csco-logo.png"
		spie_icon := prefx_imgurl_path + "/spie1.png"
		zju_icon := prefx_imgurl_path + "/ZJUniversityLogo.png"
		phd_icon := prefx_imgurl_path + "/PhD.png"

		img_CL := prefx_imgurl_path + "/sunxuCL.png"
		img_RL := prefx_imgurl_path + "/sunxuResume.png"
		sunx_avatar := prefx_imgurl_path + "/sunxuImg.png"
		sunxs_kickball := prefx_public_path + "/play_soccer0.png"
		sfggb_img_Lnk := prefx_imgurl_path + "/sfggb.webp"
		sunx_doc_RLnk := "/myresume/dwnldfile/2/"

		sunx_doc_CLnk := "/myresume/dwnldfile/1/"

		Orgs_set := map[string]string{
			"pubrmico":     pub_resume_ico,
			"wbx":          wbx_icon,
			"jbr":          jbr_icon,
			"csc":          csc_icon,
			"spie":         spie_icon,
			"zju":          zju_icon,
			"phd":          phd_icon,
			"coverletter":  img_CL,
			"resume":       img_RL,
			"kicksoccball": sunxs_kickball,
			"myavatar":     sunx_avatar,
			"docCLnk":      sunx_doc_CLnk,
			"docRLnk":      sunx_doc_RLnk,
			"ToUseLnk":     tou_doc_Lnk,
			"PPolicyLnk":   ppolicy_doc_Lnk,
			"sfggbridge":   sfggb_img_Lnk,
			"bkendSvrURL":  bkendServiceURL,
		}

		myskill_set := map[string]string{
			"csc_greenbelt":     csc_greenbelt,
			"python":            python,
			"django":            django,
			"django_restfrmwrk": django_restfrmwrk,
			"opencv":            opencv,
			"bootstrap_logo":    bootstrap_logo,
			"tcp_ico":           tcp_ico,
			"c_logo":            c_logo,
			"jira_icon":         jira_icon,
			"wireshark_ico":     wireshark_ico,
			"CPP_ico":           CPP_ico,
			"React_icon":        React_icon,
			"j2ee_ico":          j2ee_ico,
			"sringboot_ico":     sringboot_ico,
			"oracle_ico":        oracle_ico,
			"postgresql_logo":   postgresql_logo,
			"grafana_icon":      grafana_icon,
			"tableau_icon":      tableau_icon,
			"elk_ico":           elk_ico,
			"postman_icon":      postman_icon,
		}

		recogimg_fp1 := prefx_imgurl_path + "/sunxuCscAwards.png"
		recogimg_fp2 := prefx_imgurl_path + "/sunxuWbxAwards.png"

		recogimg_fp3 := prefx_secimgurl_path + "/2006_resume.png"
		recogimg_fpc1 := prefx_secimgurl_path + "/myrecog/sxu1.png"
		recogimg_fpc2 := prefx_secimgurl_path + "/myrecog/sxu2.png"
		recogimg_fpc3 := prefx_secimgurl_path + "/myrecog/sxu3.png"
		recogimg_fpc4 := prefx_secimgurl_path + "/myrecog/sxu4.png"
		recogimg_fpc5 := prefx_secimgurl_path + "/myrecog/sxu5.png"
		recogimg_fpc6 := prefx_secimgurl_path + "/myrecog/sxu6.png"
		recogimg_fpc7 := prefx_secimgurl_path + "/myrecog/sxu7.png"
		recogimg_fpc8 := prefx_secimgurl_path + "/myrecog/sxu8.png"
		recogimg_fpc9 := prefx_secimgurl_path + "/myrecog/sxu9.png"

		myrecognition_set1 := map[string]string{
			"csc_award":    recogimg_fp1,
			"wbxteo_award": recogimg_fp2,
		}

		myrecognition_set := map[string]string{
			"csc_award":          recogimg_fp1,
			"wbxteo_award":       recogimg_fp2,
			"wbx2006_award":      recogimg_fp3,
			"csc_support_award1": recogimg_fpc1,
			"csc_support_award2": recogimg_fpc2,
			"csc_support_award3": recogimg_fpc3,
			"csc_support_award4": recogimg_fpc4,
			"csc_support_award5": recogimg_fpc5,
			"csc_support_award6": recogimg_fpc6,
			"csc_support_award7": recogimg_fpc7,
			"csc_support_award8": recogimg_fpc8,
			"csc_support_award9": recogimg_fpc9,
		}

		if !isDebug {
			myrecognition_set = myrecognition_set1
		}

		slide_idx_set := map[int]string{
			0: "1",
		}

		if isDebug {
			for k := 1; k < 12; k++ {
				slide_idx_set[k] = fmt.Sprintf("%d", k+1)
			}
		} else {
			for k := 1; k < 2; k++ {
				slide_idx_set[k] = fmt.Sprintf("%d", k+1)
			}
		}

		//  language mastering status bar
		sknme2prgstatus := map[string]string{
			"Network":            "95",
			"Full Stack":         "80",
			"Production Support": "85",
			"SQL":                "91",
			"DevOPs":             "70",
			"Security":           "75",
		}
		sknme2barcolor := map[string]string{
			"Network":            "#800080",
			"Full Stack":         "#7FFFD4",
			"Production Support": "#FFC0CB",
			"SQL":                "#A52A2A",
			"DevOPs":             "#ADD8E6",
			"Security":           "#808000",
		}
		sknmedegreeLst := []map[string]string{}

		for k, v1 := range sknme2prgstatus {
			cpdata1 := map[string]string{"name": k, "valueP": v1, "valueC": sknme2barcolor[k]}
			sknmedegreeLst = append(sknmedegreeLst, cpdata1)
		}
		//resume2react.html
		//--------------- end var set --------------------------
		ctx.HTML(http.StatusOK, "resume2react.html", gin.H{
			// ========================================================================
			"sunx_doc_CLnk":  sunx_doc_CLnk,
			"orgs_set":       Orgs_set,
			"skill_set":      myskill_set,
			"dsp_idx_set":    slide_idx_set,
			"recogn_set":     myrecognition_set,
			"skl_degree_lst": sknmedegreeLst,
			//========================================================================
		})
	})
}
