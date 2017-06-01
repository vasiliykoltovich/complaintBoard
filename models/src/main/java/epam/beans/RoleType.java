package epam.beans;

import java.io.Serializable;

public enum RoleType implements Serializable {
    /**
     * Users Role admin. id used to identify the Role
     */

    ADMIN,
    /**
     * Users Role anonymous. id used to identify the Role
     */

    ANONYMOUS,
    /**
     * Users Role organization. id used to identify the Role
     */

    ORGANIZATION;

    /**
     * init constructor
     */
    RoleType() {
    }


}
