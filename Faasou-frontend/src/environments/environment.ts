// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.

export const environment = {
  production: false,
  searchApiBasePath: 'https://serpapi.com/search',
  // searchApiBasePath: 'https://serpapi.com/search/'
  searchApiKey: '18fffce420365046d3d8554abe9c2a985b52de7f5227bf11510b461f96a65d50',
  searchEngine: {
    Google: 'google',
    Yahoo: 'yahoo'
  }
};
