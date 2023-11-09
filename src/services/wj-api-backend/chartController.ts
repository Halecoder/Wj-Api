// @ts-ignore
/* eslint-disable */
import { request } from '@umijs/max';

/** getRank GET /api/chart/api_rank */
export async function getRankUsingGET(options?: { [key: string]: any }) {
  return request<Record<string, any>>('/api/chart/api_rank', {
    method: 'GET',
    ...(options || {}),
  });
}
