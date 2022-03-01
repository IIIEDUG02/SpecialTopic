```mermaid
graph TD
  A[A:載入網頁] -->B{有loginBean ?}
  B --> |Yes| C[result : username]
  B --> |No| D{有Token?}
  D --> |Yes| E{檢查Token}
  
  E --> |Yes| C
  E --> |No| F
  D --> |No| F[result : null]
  ```