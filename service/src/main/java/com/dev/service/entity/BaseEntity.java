package com.dev.service.entity;

import static java.lang.String.format;
import static java.lang.System.identityHashCode;
import static java.util.Objects.hash;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import javax.validation.constraints.Size;

@MappedSuperclass
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 3353935735394492810L;

    @Id
    @Size(min = 1, max = 255)
    private String code;

    @Version
    private int version;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        BaseEntity other = (BaseEntity) obj;
        if (code == null) {
            if (other.code != null) {
                return false;
            }
        } else if (!code.equals(other.code)) {
            return false;
        }
        return true;
    }

    public String getCode() {
        return code;
    }

    public int getVersion() {
        return version;
    }

    @Override
    public int hashCode() {
        return hash(code);
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return format("%s: code=%s(%s) | version=%s", getClass().getSimpleName(), code, identityHashCode(this),
                version);
    }
}
