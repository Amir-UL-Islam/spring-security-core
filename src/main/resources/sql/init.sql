-- Table: t_sys_user (System User Table)
CREATE TABLE t_sys_user (
                            id INT AUTO_INCREMENT PRIMARY KEY COMMENT 'primary key ID',
                            username VARCHAR(100) NOT NULL UNIQUE COMMENT 'Account',
                            password VARCHAR(255) NOT NULL COMMENT 'Login password',
                            pwd VARCHAR(255) NOT NULL COMMENT 'Clear text password',
                            nick_name VARCHAR(100) NOT NULL COMMENT 'nickname',
                            sex VARCHAR(1) COMMENT 'Gender 0: Male 1: Female',
                            phone VARCHAR(11) NOT NULL COMMENT 'Mobile phone number',
                            email VARCHAR(100) NOT NULL COMMENT 'Email',
                            avatar VARCHAR(255) COMMENT 'avatar',
                            flag VARCHAR(10) COMMENT 'status',
                            salt VARCHAR(50) COMMENT 'salt value',
                            token VARCHAR(255) COMMENT 'token',
                            qq_oppen_id VARCHAR(100) COMMENT 'QQ third-party login Open_ID unique identifier',
                            gmt_create DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Creation date',
                            gmt_modified DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'modification time'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='System Management-User Basic Information Table';

-- Table: t_sys_role (System Role Table)
CREATE TABLE t_sys_role (
                            id INT AUTO_INCREMENT PRIMARY KEY COMMENT 'primary key ID',
                            code VARCHAR(20) NOT NULL UNIQUE COMMENT 'role code',
                            name VARCHAR(100) NOT NULL COMMENT 'Role name',
                            remarks VARCHAR(500) COMMENT 'role description',
                            gmt_create DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Creation date',
                            gmt_modified DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'modification time'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='System Management-Role Table';

-- Table: t_sys_menu (System Menu Table)
CREATE TABLE t_sys_menu (
                            id INT AUTO_INCREMENT PRIMARY KEY COMMENT 'primary key',
                            parent_id VARCHAR(50) COMMENT 'Previous menu ID',
                            url VARCHAR(255) COMMENT 'url',
                            resources VARCHAR(100) NOT NULL UNIQUE COMMENT 'menu code',
                            title VARCHAR(100) NOT NULL COMMENT 'menu name',
                            level INT COMMENT 'menu level',
                            sort_no INT COMMENT 'sort',
                            icon VARCHAR(100) COMMENT 'menu icon',
                            type VARCHAR(20) NOT NULL COMMENT 'Type menu, button',
                            remarks VARCHAR(500) COMMENT 'Remarks',
                            gmt_create DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Creation date',
                            gmt_modified DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'modification time'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='System Management-Menu Table';

-- Table: t_sys_user_role (User-Role Association Table)
CREATE TABLE t_sys_user_role (
                                 id INT AUTO_INCREMENT PRIMARY KEY COMMENT 'primary key',
                                 user_id INT NOT NULL COMMENT 'User ID',
                                 role_id INT NOT NULL COMMENT 'role ID',
                                 gmt_create DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Creation date',
                                 gmt_modified DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'modification time',
                                 FOREIGN KEY (user_id) REFERENCES t_sys_user(id) ON DELETE CASCADE,
                                 FOREIGN KEY (role_id) REFERENCES t_sys_role(id) ON DELETE CASCADE,
                                 UNIQUE KEY uk_user_role (user_id, role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='System Management-User Role Association Table';

-- Table: t_sys_role_menu (Role-Menu Association Table)
CREATE TABLE t_sys_role_menu (
                                 id INT AUTO_INCREMENT PRIMARY KEY COMMENT 'primary key',
                                 role_id INT NOT NULL COMMENT 'role ID',
                                 menu_id INT NOT NULL COMMENT 'menu ID',
                                 gmt_create DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Creation date',
                                 gmt_modified DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'modification time',
                                 FOREIGN KEY (role_id) REFERENCES t_sys_role(id) ON DELETE CASCADE,
                                 FOREIGN KEY (menu_id) REFERENCES t_sys_menu(id) ON DELETE CASCADE,
                                 UNIQUE KEY uk_role_menu (role_id, menu_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='System Management-Role Menu Association Table';

-- Table: t_sys_log (System Log Table)
CREATE TABLE t_sys_log (
                           id INT AUTO_INCREMENT PRIMARY KEY COMMENT 'primary key ID',
                           name VARCHAR(200) COMMENT 'Interface name',
                           url VARCHAR(500) COMMENT 'Interface address',
                           ip VARCHAR(50) COMMENT 'Visitor IP',
                           user_id INT COMMENT 'Visitor ID',
                           status INT COMMENT 'status',
                           execute_time VARCHAR(50) COMMENT 'Interface execution time',
                           gmt_create DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Creation date',
                           gmt_modified DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'modification time',
                           FOREIGN KEY (user_id) REFERENCES t_sys_user(id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='System Management-Log Table';

-- Create indexes for better query performance
CREATE INDEX idx_username ON t_sys_user(username);
CREATE INDEX idx_token ON t_sys_user(token);
CREATE INDEX idx_role_code ON t_sys_role(code);
CREATE INDEX idx_menu_resources ON t_sys_menu(resources);
CREATE INDEX idx_menu_parent ON t_sys_menu(parent_id);
CREATE INDEX idx_user_role_user ON t_sys_user_role(user_id);
CREATE INDEX idx_user_role_role ON t_sys_user_role(role_id);
CREATE INDEX idx_role_menu_role ON t_sys_role_menu(role_id);
CREATE INDEX idx_role_menu_menu ON t_sys_role_menu(menu_id);
CREATE INDEX idx_log_user ON t_sys_log(user_id);
CREATE INDEX idx_log_create_time ON t_sys_log(gmt_create);