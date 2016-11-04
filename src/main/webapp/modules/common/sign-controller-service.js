app.service("guestSignService",function(RequestService){
  this.getGuestByEmail = function(email){
    return RequestService.getRequest('/guest/getGuestByEmail',email,cfg_form)
  };

  this.getAllGuests = function(){
    return RequestService.getRequest('/guest/getAllGuests',null,cfg_form);
  }
})