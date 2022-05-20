select  id, codigo_tratamiento, fecha_inicio, fecha_fin, tipo_tratamiento, id_mascota, id_servicio, valor
from adnveterinaria.tratamiento
where id = :id