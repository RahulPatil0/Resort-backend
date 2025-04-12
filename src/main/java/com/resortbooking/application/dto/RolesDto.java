package com.resortbooking.application.dto;

/**
 * Data Transfer Object for Roles.
 */
public class RolesDto {

    private Long id;
    private String roleName;
    private String description;

    // Constructors
    public RolesDto() {}

    public RolesDto(Long id, String roleName, String description) {
        this.id = id;
        this.roleName = roleName;
        this.description = description;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // toString

    @Override
    public String toString() {
        return "RolesDto{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
