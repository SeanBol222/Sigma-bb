-- Países
INSERT INTO public.pais(
    k_id_pais, n_nombre_pais, b_estado_activo
)
VALUES
    ('COL', 'Colombia', true),
    ('MEX', 'Mexico', true),
    ('ARG', 'Argentina', true),
    ('PER', 'Peru', true),
    ('CHI', 'Chile', true);

-- Ciudades de Colombia
INSERT INTO public.ciudad(
    k_id_ciudad, n_nombre_ciudad, b_estado_activo, k_id_pais
)
VALUES
    ('BOG', 'Bogota', true, 'COL'),
    ('MED', 'Medellin', true, 'COL'),
    ('CAL', 'Cali', true, 'COL'),
    ('BAR', 'Barranquilla', true, 'COL'),
    ('CTG', 'Cartagena', true, 'COL'),
    ('BUC', 'Bucaramanga', true, 'COL'),
    ('MAN', 'Manizales', true, 'COL'),
    ('PERC', 'Pereira', true, 'COL');

-- Ciudades de México
INSERT INTO public.ciudad(
    k_id_ciudad, n_nombre_ciudad, b_estado_activo, k_id_pais
)
VALUES
    ('CDMX', 'Ciudad de Mexico', true, 'MEX'),
    ('GDL', 'Guadalajara', true, 'MEX'),
    ('MTY', 'Monterrey', true, 'MEX');

-- Ciudades de Argentina
INSERT INTO public.ciudad(
    k_id_ciudad, n_nombre_ciudad, b_estado_activo, k_id_pais
)
VALUES
    ('BUE', 'Buenos Aires', true, 'ARG'),
    ('COR', 'Cordoba', true, 'ARG'),
    ('ROS', 'Rosario', true, 'ARG');

-- Ciudades de Perú
INSERT INTO public.ciudad(
    k_id_ciudad, n_nombre_ciudad, b_estado_activo, k_id_pais
)
VALUES
    ('LIM', 'Lima', true, 'PER'),
    ('ARE', 'Arequipa', true, 'PER'),
    ('CUS', 'Cusco', true, 'PER');

-- Ciudades de Chile
INSERT INTO public.ciudad(
    k_id_ciudad, n_nombre_ciudad, b_estado_activo, k_id_pais
)
VALUES
    ('SCL', 'Santiago', true, 'CHI'),
    ('VAL', 'Valparaiso', true, 'CHI'),
    ('CON', 'Concepcion', true, 'CHI');
