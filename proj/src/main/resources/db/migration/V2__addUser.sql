INSERT INTO users(
	id,
	email,
	password,
	status,
	role,
	language,
	skin
) VALUES (
    1,
    'admin@admin.com',
    '$2a$12$tI9jgIFYqzpNtx6XgkcHUeH9Mif7hc1Owt99m/tcu4D5ivXW2ZOS.',
    'ACTIVE',
    'ROLE_ADMIN',
    'ENGLISH',
    'DARK'
 );

INSERT INTO users(
	id,
	email,
	password,
	status,
	role,
	language,
	skin
) VALUES (
    2,
    'user@user.com',
    '$2a$12$WmLK.1.Gp3bCyey6.4iAsufn8N6Cv9zkmNqcL2x3DK835R7pnWs4m',
    'ACTIVE',
    'ROLE_USER',
    'ENGLISH',
    'DARK'
 );