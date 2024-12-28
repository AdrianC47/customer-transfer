-- Inserciones para la tabla Cliente
INSERT INTO Cliente (id, nombre, cedula, telefono) VALUES (1, 'Juan Perez', '0102030405', '0987654321');
INSERT INTO Cliente (id, nombre, cedula, telefono) VALUES (2, 'Maria Gomez', '0203040506', '0987654322');
INSERT INTO Cliente (id, nombre, cedula, telefono) VALUES (3, 'Carlos Ruiz', '0304050607', '0987654323');
INSERT INTO Cliente (id, nombre, cedula, telefono) VALUES (4, 'Ana Martinez', '0405060708', '0987654324');

-- Inserciones para la tabla Cuenta
INSERT INTO Cuenta (codigo, cliente_id, numeroCuenta, saldo) VALUES (1, 1, 1001, 500.00);
INSERT INTO Cuenta (codigo, cliente_id, numeroCuenta, saldo) VALUES (2, 1, 1002, 1500.00);
INSERT INTO Cuenta (codigo, cliente_id, numeroCuenta, saldo) VALUES (3, 2, 2001, 300.00);
INSERT INTO Cuenta (codigo, cliente_id, numeroCuenta, saldo) VALUES (4, 3, 3001, 1200.00);
INSERT INTO Cuenta (codigo, cliente_id, numeroCuenta, saldo) VALUES (5, 4, 4001, 800.00);
INSERT INTO Cuenta (codigo, cliente_id, numeroCuenta, saldo) VALUES (6, 4, 4002, 2500.00);
