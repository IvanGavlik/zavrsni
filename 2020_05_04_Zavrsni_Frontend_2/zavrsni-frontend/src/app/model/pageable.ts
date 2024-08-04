export interface Pageable<E> {
  pageItems: E;
  pageIndex: number;
  pageSize: number;
  totalElements: number;
  totalPages: number;
}
