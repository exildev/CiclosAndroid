$mdThemingProvider.definePalette('zol', {
  '50': '#b2ddff',
  '100': '#66bbff',
  '200': '#2ea3ff',
  '300': '#0080e5',
  '400': '#006fc7',
  '500': '#005ea8',
  '600': '#004d89',
  '700': '#003c6b',
  '800': '#002b4c',
  '900': '#001a2e',
  'A100': '#b2ddff',
  'A200': '#66bbff',
  'A400': '#006fc7',
  'A700': '#003c6b',
  'contrastDefaultColor': 'light',
  'contrastDarkColors': '50 100 200 A100 A200'
});

$mdThemingProvider.definePalette('rol', {
  '50': '#ffecee',
  '100': '#ffa0ab',
  '200': '#ff6879',
  '300': '#ff203a',
  '400': '#ff021f',
  '500': '#e2001a',
  '600': '#c30016',
  '700': '#a50013',
  '800': '#86000f',
  '900': '#68000c',
  'A100': '#ffecee',
  'A200': '#ffa0ab',
  'A400': '#ff021f',
  'A700': '#a50013',
  'contrastDefaultColor': 'light',
  'contrastDarkColors': '50 100 200 A100 A200'
});

$mdThemingProvider.theme('olimpica')

	.primaryPalette('zol')

	.accentPalette('rol');
