INSERT INTO descriptor VALUES   (1, 'Construcción'),
                                (2, 'Saneamiento básico'),
                                (3, 'Energías alternas'),
                                (4, 'Redes y Sistemas de Información');

INSERT INTO subDescriptor(name, idDescriptor) VALUES
    ('Materiales', 1),
    ('Sistemas Constructivos', 1),
    ('Elementos Constructivos', 1),
    ('Procesos Constructivos', 1),
    ('Productos Constructivos', 1),
    ('I-Sistemas de Información', 1),
    ('Agua Potable', 2),
    ('Agua Servidas', 2),
    ('Biogas y Energías Renovables', 2),
    ('Basura', 2),
    ('Disposición de Excretas', 2),
    ('II-Sistemas de Información', 2),
    ('Eólica', 3),
    ('Solar', 3),
    ('Fotovoltálica', 3),
    ('Biogas', 3),
    ('Biodisel', 3),
    ('Biocombustible', 3),
    ('Hiráulica', 3),
    ('Renovables', 3),
    ('Geotérmica', 3),
    ('Biomasa', 3),
    ('III-Sistemas de Información', 3);

INSERT INTO participation(code, name) VALUES
    ('A', 'Investigación y/o desarrollo de tecnologías'),
    ('B', 'Planificación'),
    ('C', 'Apoyo técnico'),
    ('D', 'Capacitación'),
    ('E', 'Infraestructura'),
    ('F', 'Extensión'),
    ('G', 'Difusión'),
    ('H', 'Créditos');
