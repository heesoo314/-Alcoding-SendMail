package spring.control;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import spring.service.MailService;

@Controller
public class MailControl {

	@Autowired
	private MailService mailService;
	
	@RequestMapping("/send_mail.al")
	public ModelAndView sendMail() throws Exception{
		// 여기서 생기는 의문?
		// 1. 상대의 이메일 주소를 회원id로 하여 DB에서 가져올까?
		// 2. 현재 메소드를 수행할 때 파라미터로 넘어올까?
				
		String toAddress = "real_heesoo@naver.com";
		String fromAddress = "heesoo.kim314@gmail.com";
		
		
		// 1번의 경우, MemberVO vo가 null이 아님을 판단
		
		/* if(mvo != null){ ... }
		 * String toAddress = mvo.getId(); */
		
		
		// 임시비밀번호 6자리 생성
		int[] ar = new int[6];
		
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<ar.length; i++){
			ar[i] = ((int)(Math.random()*10));
			sb.append(ar[i]);
			// ar[i] = new Random().nextInt(10);
			
		}
		
		// 비밀번호는 DB에 암호화한 후 저장 (* Salt!)
/*		String pwd = SecureUtil.getEncrypt(sb.toString(), mvo.getSalt());
		dao.changePwd(mvo.getId(), pwd);*/
		
		
		String subject = "[알림] 임시 비밀번호가 발급되었습니다";
		String msgBody = "안녕하세요? 000님 ...........\n임시 비밀번호는 다음과 같습니다:" + sb.toString();
		
		mailService.sendEmail(toAddress, fromAddress, subject, msgBody);
		
		
				
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg", "메일발송 정상!");
		mv.setViewName("/send_mail");
		return mv;
		
	}
	
}
