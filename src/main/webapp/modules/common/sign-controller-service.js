app.service("guestSignService",function(RequestService){
  this.getGuestByEmail = function(email){
    return RequestService.postRequest('/guest/getGuestByEmail', $.param({"email":email}),cfg_form);
    /*return RequestService.getRequest('/guest/getGuestByEmail/'+email,cfg_form);*/
  };

  this.getAllGuests = function(){
    return RequestService.getRequest('/guest/getAllGuests',null,cfg_form);
  }

  this.addOrUpdateGuest = function(guest){
    var url = guest.id?"/guest/updateGuest":"/guest/addGuest";
    return RequestService.postRequest(url, $.param({"json":JSON.stringify(guest)}),cfg_form);
  }
})