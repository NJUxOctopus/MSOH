package ui.view.controllerservice;

import vo.CustomerVO;
import vo.MemberVO;

/**
 * Created by apple on 16/11/10.
 */
public interface ProcessMember {
    public void upGrade(MemberVO memberVO, CustomerVO customerVO);

    public void deGrade(MemberVO memberVO, CustomerVO customerVO);
}
