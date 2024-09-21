package E2E_Test;

import TestRegisteration.*;

import java.util.Scanner;

public class RegisterationProcess {
    public static void main(String[] args) {
        Registeration reg = new Registeration();
        VerifyRegOTP vt = new VerifyRegOTP();
        LogIn lg = new LogIn();
        LogOut logOut = new LogOut();

        try{
            reg.registerUser();
            System.out.println("Enter OTP registration:");
            Scanner scanner = new Scanner(System.in);
            String OTP = scanner.nextLine();
            vt.VerifiyRegisterOTP(OTP);
            String token = lg.loginUser();
            logOut.logOut(token);
        }catch(AssertionError e){
            System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
            System.out.println("Test Failed");
            System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
            System.out.println(e.getMessage());
        }
    }
}
