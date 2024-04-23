package com.ohgiraffers.crud.menu.model.dto;

public class categoryAndMenuDTO {
    private int code;
    private String name;

    private int refCategoryCode;
    private MenuDTO menuDTO;

    public categoryAndMenuDTO() {
    }

    public categoryAndMenuDTO(int code, String name, int refCategoryCode, MenuDTO menuDTO) {
        this.code = code;
        this.name = name;
        this.refCategoryCode = refCategoryCode;
        this.menuDTO = menuDTO;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRefCategoryCode() {
        return refCategoryCode;
    }

    public void setRefCategoryCode(int refCategoryCode) {
        this.refCategoryCode = refCategoryCode;
    }

    public MenuDTO getMenuDTO() {
        return menuDTO;
    }

    public void setMenuDTO(MenuDTO menuDTO) {
        this.menuDTO = menuDTO;
    }

    @Override
    public String toString() {
        return "categoryAndMenuDTO{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", refCategoryCode=" + refCategoryCode +
                ", menuDTO=" + menuDTO +
                '}';
    }
}