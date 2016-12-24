package util;

public enum ResultMessage {
    Login_CustomerSuccess, Blank, Login_NoUser, Login_WrongPassword,Login_ClerkSuccess,Login_ManagerSuccess,Login_MarketerSuccess,
    Customer_SignupSuccess, Customer_SignupExist,
    ChangeInfoSuccess, Customer_CustomerNotExist,Hotel_changeAvailableRoomSuccess,Hotel_changeReservedRoomSuccess,Hotel_changeOccupiedRoomSuccess,
    ChangePasswordWrongOldPw, ChangePassword2DifferentNew, ChangePasswordSuccess,
    Member_NormalSignupSuccess, Member_EnterpriseSignupSuccess, RoomNotEnough,
    Order_CreateOrderSuccess,Order_RenewOrderSuccess,
    Hotel_AddRoomSuccess, Hotel_HotelNotExist,Hotel_deleteHotelSuccess,Hotel_ModifyDailyRoomInfoSuccess,Hotel_modifyHotelInfoSuccess,
    Manager_ManagerNotExist,Customer_isNotMember,levelNotChange,levelChangeSuccess,emailFormatWrong,phoneFormatWrong,
   Clerk_ClerkNotExist,Hotel_HasClerk,Password_isRemembered,Password_rememberSuccess,Password_CancelRememberSuccess,Customer_AddCreditRecordSuccess,
    Marketer_MarketerNotExist,Hotel_addHotelSuccess,Fail,
    PromotionNotExist,Promotion_DeletePromotionSuccess,DataFormatWrong,
    Promotion_AddPromotionSuccess, Order_EndOrderSuccess, Order_ExecuteOrderSuccess, Order_CancelOrderSuccess,
    Marketer_ChangeInfoSuccess,  Marketer_MarketerAlreadyExist,
    Member_AddMemberAlreadyExist,
    MemberLevel_ModifyMemberLevelSuccess,
    Order_ChangeOrderStatusSuccess,
    Manager_ChangeManagerInfoSuccess,Hotel_addCommentSuccess,
    Clerk_AddClerkSuccess,Clerk_AddClerkExist,Clerk_DeleteClerkNotExist,Clerk_DeleteClerkSuccess,Order_notExist,Order_CancelOrderBetweenSixHour,
    Promotion_ModifyPromotionSuccess,Marketer_DeleteMarketerSuccess,Marketer_AddMarketerSuccess,Hotel_deleteClerkSuccess;
}
