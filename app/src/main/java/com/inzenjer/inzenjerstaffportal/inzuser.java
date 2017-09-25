package com.inzenjer.inzenjerstaffportal;

public class inzuser {


        private String username, email, createdate;

        public inzuser(String username, String email, String phoneno , String createdate) {
            this.username = username;
            this.email = email;
            this.createdate=createdate;
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
