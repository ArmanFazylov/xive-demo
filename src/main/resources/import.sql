--login: user, pass: ro34@11dfdk3
insert into account (id, username, password, is_enabled, is_locked, is_expired) values (1, 'user', '$2b$10$2enmXHrp1.SceEgoiMBNreJBdzm.yNPkQgj7LfP/yHVJ6ftNfzfb.', true, false, false);
--login: admin, pass: @tmax112
insert into account (id, username, password, is_enabled, is_locked, is_expired) values (2, 'admin', '$2b$10$DOc5O2fYVddduq60XUq0m.NUJtwTd8gQIF9bxwxk/PI0w/4kRSv1a', true, false, false);
--login: actuator, pass: @actu@tr1
insert into account (id, username, password, is_enabled, is_locked, is_expired) values (3, 'actuator', '$2b$10$CF9faS.xKOtndzJyV8hMFe3Qhq1buGpC3qKpKDesuuQF5rNfkjY.u', true, false, false);
INSERT INTO authority(name, id) VALUES ('ROLE_USER', 1);
INSERT INTO authority(name, id) VALUES ('ROLE_ADMIN', 2);
INSERT INTO authority(name, id) VALUES ('ROLE_ACTUATOR', 3);
INSERT INTO account_to_authority (authority_id, account_id) VALUES (1, 1);
INSERT INTO account_to_authority (authority_id, account_id) VALUES (2, 2);
INSERT INTO account_to_authority (authority_id, account_id) VALUES (3, 3);