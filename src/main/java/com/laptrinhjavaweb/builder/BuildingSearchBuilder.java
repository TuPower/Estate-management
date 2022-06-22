package com.laptrinhjavaweb.builder;

public class BuildingSearchBuilder {
	
	private String name;
	private String district;
	private String buildingArea;
	private String street;
	private String ward;
	private Integer numberOfBasement;
	private String[] types = new String[] {};
	private Integer costRentFrom;
	private Integer costRentTo;
	private Integer areaRentFrom;
	private Integer areaRentTo;
	private Integer staffId;
	private String managerName;
	private String managerPhone;

	public String getManagerName() {
		return managerName;
	}

	public String getManagerPhone() {
		return managerPhone;
	}

	public String getName() {
		return name;
	}

	public String getDistrict() {
		return district;
	}
	
	public String getBuildingArea() {
		return buildingArea;
	}

	public Integer getNumberOfBasement() {
		return numberOfBasement;
	}
	
	public String getStreet() {
		return street;
	}

	public String getWard() {
		return ward;
	}
	
	public String[] getTypes() {
		return types;
	}
	
	public Integer getCostRentFrom() {
		return costRentFrom;
	}

	public Integer getCostRentTo() {
		return costRentTo;
	}

	public Integer getAreaRentFrom() {
		return areaRentFrom;
	}

	public Integer getAreaRentTo() {
		return areaRentTo;
	}
	
	public Integer getStaffId() {
		return staffId;
	}
	
	private BuildingSearchBuilder(Builder builder) {
		this.name = builder.name;
		this.district = builder.district;
		this.buildingArea = builder.buildingArea;
		this.numberOfBasement = builder.numberOfBasement;
		this.street = builder.street;
		this.ward = builder.ward;
		this.types = builder.types;
		this.costRentFrom = builder.costRentFrom;
		this.costRentTo = builder.costRentTo;
		this.areaRentFrom = builder.areaRentFrom;
		this.areaRentTo = builder.areaRentTo;
		this.staffId = builder.staffId;
		this.managerName = builder.managerName;
		this.managerPhone= builder.managerPhone;

	}
	
	public static class Builder {

		private String name;
		private String district;
		private String street;
		private String ward;
		private String buildingArea;
		private Integer numberOfBasement;
		private String[] types = new String[] {};
		private Integer costRentFrom;
		private Integer costRentTo;
		private Integer areaRentFrom;
		private Integer areaRentTo;
		private Integer staffId;
		private String managerName;
		private String managerPhone;

		public Builder setManagerName(String managerName) {
			this.managerName = managerName;
			return this;
		}

		public Builder setManagerPhone(String managerPhone) {
			this.managerPhone = managerPhone;
			return this;
		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}
		
		public Builder setDistrict(String district) {
			this.district = district;
			return this;
		}

		public Builder setBuildingArea(String buildingArea) {
			this.buildingArea = buildingArea;
			return this;
		}

		public Builder setNumberOfBasement(Integer numberOfBasement) {
			this.numberOfBasement = numberOfBasement;
			return this;
		}
		
		public Builder setStreet(String street) {
			this.street = street;
			return this;
		}

		public Builder setWard(String ward) {
			this.ward = ward;
			return this;
		}
		
		public Builder setTypes(String[] types) {
			this.types = types;
			return this;
		}

		public Builder setCostRentFrom(Integer costRentFrom) {
			this.costRentFrom = costRentFrom;
			return this;
		}

		public Builder setCostRentTo(Integer costRentTo) {
			this.costRentTo = costRentTo;
			return this;
		}

		public Builder setAreaRentFrom(Integer areaRentFrom) {
			this.areaRentFrom = areaRentFrom;
			return this;
		}

		public Builder setAreaRentTo(Integer areaRentTo) {
			this.areaRentTo = areaRentTo;
			return this;
		}
		
		public Builder setStaffId(Integer staffId) {
			this.staffId = staffId;
			return this;
		}

		public BuildingSearchBuilder build() {

			return new BuildingSearchBuilder(this);
		}
	}
}
