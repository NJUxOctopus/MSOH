package util;

public enum ResultMessage {
    Login_Success, Blank, Login_NoUser, Login_WrongPassword,
    Customer_SignupSuccess, WrongPhoneFormat, WrongIDFormat, WrongEmailFormat, Customer_SignupExist,
    ChangeInfoSuccess, Customer_CustomerNotExist,
    ChangePasswordWrongOldPw, ChangePassword2DifferentNew, ChangePasswordSuccess,
    Member_NormalSignupSuccess, Member_EnterpriseSignupSuccess, Member_SignupCreditNotEnough,
    Order_CreateOrderSuccess,Order_SetAbnormalSuccess,Order_RenewOrderSuccess,
    Hotel_AddRoomSuccess, Hotel_HotelNotExist,
    Manager_AddCustomerAlreadyExist, Manager_AddCustomerSuccess, Manager_ChangeCustomerInfoSuccess,
    Manager_AddClerkAlreadyExist, Manager_AddClerkSuccess, Manager_ManagerNotExist,
    Manager_DeleteClerkSuccess, Clerk_ClerkNotExist, Manager_ChangeClerkInfoSuccess, Manager_AddMarketerSuccess,
    Marketer_MarketerNotExist, Manager_DeleteMarketerSuccess, Manager_ChangeMarketerInfoSuccess,
    Hotel_HotelAlreadyExist, WrongHotelIDFormat, Manager_AddHotelSuccess, Manager_DeleteHotelSuccess, Manager_ChangeHotelInfoSuccess,
    Promotion_AddPromotionSuccess, Order_EndOrderSuccess, Order_ExecuteOrderSuccess, Order_CancelOrderSuccess,
    Marketer_ChangeInfoSuccess, Marketer_CreditChargeSuccess, Marketer_MarketerAlreadyExist,
    Member_AddMemberAlreadyExist, Member_AddMemberSuccess, Member_Upgrade, Member_Degrade, Manager_DeleteMemberSuccess, Member_MemberNotExist,
    MemberLevel_AddMemberLevelSuccess, MemberLevel_ModifyMemberLevelSuccess,
    Order_AddOrderSuccess, Order_ChangeOrderStatusSuccess, Order_SetActualCheckinTimeSuccess, Order_SetActualCheckoutTimeSuccess,
    Order_ChangeOrderStatusFailure, Order_SetActualCheckinTimeFailure, Order_SetActualCheckoutTimeFailure,
    Hotel_ModifyRoomSuccess, Hotel_DeleteRoomSuccess, Hotel_setDailyRoomInfoSuccess, Hotel_addCommentSuccess, Hotel_addToListOfHotelReservedByCustomerSuccess, Manager_AddMarketerAlreadyExist, Manager_ChangeManagerInfoSuccess,
    Clerk_AddClerkSuccess,Clerk_AddClerkExist,Clerk_DeleteClerkNotExist,Clerk_DeleteClerkSuccess,
    Promotion_ModifyPromotionSuccess;
}
