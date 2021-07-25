// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.

const basePath: string = 'https://localhost:8075';
const defaultCx: string = 'b4ced864ac282433c';

export const environment = {
  production: false,
  basePath: basePath,
  defaultCx: defaultCx
};
