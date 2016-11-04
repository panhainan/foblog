app.service("guestSignService",function(RequestService){
  this.getGuestByEmail = function(email){
    return RequestService.postRequest('/guest/getGuestByEmail', $.param({"email":email}),cfg_form)
  };

  this.getAllGuests = function(){
    return RequestService.getRequest('/guest/getAllGuests',null,cfg_form);
  }
})