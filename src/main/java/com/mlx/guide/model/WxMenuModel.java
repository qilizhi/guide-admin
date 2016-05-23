package com.mlx.guide.model;


import java.util.List;

public class WxMenuModel {
  
	
	List<Button> button;
	
	public static class Button{
		private String type;
		
		private String name;
		
		private String url;
		
		private List<SubButton> sub_button;
		
		public static class  SubButton{
        		 
        		 private String type="view";
        		 private String name;
        		 private String url;
        		 
        		 
        		 
				public String getType() {
					return type;
				}
				public void setType(String type) {
					this.type = type;
				}
				public String getName() {
					return name;
				}
				public void setName(String name) {
					this.name = name;
				}
				public String getUrl() {
					return url;
				}
				public void setUrl(String url) {
					this.url = url;
				} 

		}
			public String getType() {
				return type;
			}
			
			public void setType(String type) {
				this.type = type;
			}
			
			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			public String getUrl() {
				return url;
			}

			public void setUrl(String url) {
				this.url = url;
			}

			public List<SubButton> getSub_button() {
				return sub_button;
			}

			public void setSub_button(List<SubButton> sub_button) {
				this.sub_button = sub_button;
			}

	}

	public List<Button> getButton() {
		return button;
	}

	public void setButton(List<Button> button) {
		this.button = button;
	}

}
