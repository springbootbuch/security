insert into users(login, hashed_password) values('Michael', '{bcrypt}$2a$06$wCQKuapy2XKPOeNV7kDBsOVBStUvYtepzBYvVCm9zfQYWlt56HLCm');
insert into users(login, hashed_password) values('Insecure', '{noop}Yes,really');
insert into users(login, hashed_password) values('Klaus', '{sha256}478c847c34df58bb68f3c24ecb4468a9778f2b41703d6a8ac74ced562f2e566294695134fbf5526f');
insert into users(login, hashed_password) values('Rainer', '{pbkdf2}c228e5adfdab91cb90f881412c431e36987c3eba6fb2fd70d9f760f8bf275cd2281e86bab8c559af');