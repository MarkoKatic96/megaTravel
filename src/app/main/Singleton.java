package app.main;

public class Singleton {
	private static Singleton instance = null;
	
	private String token = "";
	
	private Singleton() {
	}
	
	public static Singleton getInstance() {
		if (instance == null) 
			instance = new Singleton(); 
  
        return instance; 
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
