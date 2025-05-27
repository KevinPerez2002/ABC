import { RenderMode, ServerRoute } from '@angular/ssr';
import {routes} from './app.routes';

export const serverRoutes: ServerRoute[] = [
  ...routes.map(route => {
    return route.path === 'roles'
      ? {
        path: route.path || '',
        renderMode: RenderMode.Prerender
      } as const
      : {
        path: route.path || '',
        renderMode: RenderMode.Server
      } as const;
  }),
  {
    path: '**',
    renderMode: RenderMode.Prerender
  }
];
