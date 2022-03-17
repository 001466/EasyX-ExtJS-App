Ext.define("App.view.login.LoginController", {
	extend: "Ext.app.ViewController",
	alias: "controller.login",
	uses: ["App.view.main.Main"],
	
	onSpecialKey: function(field, e) {
		if(e.getKey() == e.ENTER) {
			this.doLogin();
		}
	},
	
	onLogin: function() {
		this.doLogin();
	},
	
	doLogin: function() {
		var form = this.lookupReference("loginForm");
		var me=this;
		if(form.isValid()) {

		     form.submit({
		        scope:this,
                success: function(form, action) {

                   __usr=action.result.data;
                   __tkn=__usr.access_token;

                   me.getView().destroy();
                   Ext.create("App.view.main.Main");
                   Ext.getCmp("loginDisplayLabel").setText(__usr.username);
                   window.sessionStorage.setItem("Authorization", __tkn);


                   Ext.Ajax.on("beforerequest",	function(    conn,   options,   eOpts) {
                        conn.setUseDefaultXhrHeader(false);
                        conn.setWithCredentials(true);
                        Ext.apply(options, {
                            headers:{
                                "Authorization":__tkn
                            }
                        });
                   }, me);


                },
                failure: function(form, action) {
                    Ext.Msg.alert('Failed', action.result.message);
                }
             });

		}
	}
});












