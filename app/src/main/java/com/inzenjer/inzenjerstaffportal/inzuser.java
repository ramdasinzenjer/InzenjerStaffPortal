package com.inzenjer.inzenjerstaffportal;

public class inzuser {


        private String username, email, createdate , password;

        public inzuser(String username, String email, String phoneno ,String password , String createdate) {
            this.username = username;
            this.email = email;
            this.createdate=createdate;
            this.password=password;

        }
        public String getpassword()
        {
            return password;
        }

        public String getUsername() {
            return username;
        }

        public String getEmail() {
            return email;
        }

        public String getCreatedate()
        {
            return createdate;
        }

}
