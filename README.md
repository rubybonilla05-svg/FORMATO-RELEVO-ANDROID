# FORMATO RELEVO — primera versión Android

Este proyecto es un **prototipo funcional** para Android basado en `FORMATO RELEVO.xlsx`.

## Incluye

- Operario, turno y fecha.
- Lecturas digitales para los 8 campos A..H.
- Lecturas mecánicas.
- Créditos Islero 1 / Islero 2.
- Pagos y abonos.
- Entregas diarias.
- Lubricantes.
- Gastos / salidas.
- Cálculos automáticos basados en las fórmulas del Excel.
- Confirmación y bloqueo del turno.
- Generación de PDF después de confirmar.

## Abrir y compilar

1. Instalar Android Studio.
2. Abrir la carpeta `FORMATO_RELEVO_ANDROID`.
3. Esperar a que Gradle sincronice.
4. Conectar un teléfono Android o iniciar un emulador.
5. Ejecutar `app`.
6. Para generar APK: `Build > Build APK(s)`.

## Importante

Esta es la **primera versión de trabajo**, no la versión final de producción. Antes del APK definitivo hay que validar cada campo contra el Excel con un relevo real, especialmente créditos, entregas, lubricantes, pagos, gastos y caja.

La app usa `PdfDocument` de Android para crear el PDF sin necesitar internet.
