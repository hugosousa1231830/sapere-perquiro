# sapere-perquiro

This repository functions as a personal sandbox for the exploration and experimentation of various projects aimed at 
acquiring new skills and developing practical solutions. While it offers valuable opportunities for learning and 
experimentation, it should not be regarded as a comprehensive source of authoritative knowledge or guidance. The content 
within this repository primarily serves to document my learning experiences and to provide insight into my ongoing projects.

## Projects

1. **Smart-Home**: 
This project was developed as part of my Software Development training course (Switch). It is not 
intended to replicate a full-fledged smart home system. Many features are not implemented, as the project adhered 
strictly to the defined user stories and did not extend beyond them.

2. **CEXscraper**: 
This project represents a personal aspiration that predates my coding journey. It is designed as a 
specialized application and may be challenging to replicate, as it relies on an Excel file with specific entries that 
I am unable to share.


## Learned concepts
1. Asynchronous tasks (CEXScraper):
   Tackling async: While searching for a way to sort this I came across the notion of future and completable future, which seems to be something related to java 8.
   A future object represents the result of an async computation and acts as a placeholder for a value that will be available at some point. The future object can be
   queried to check the status of the computation, retrieve the results once they are available or cancel. To do this there are methods like: 
get() - This waits for the computation to complete and retrieves the result
isDone() - Checks if complete
cancel() - Cancels the computation
isCancelled() - Checks if the computation was canceled

A completableFuture is an implementation of future and provides
   additional methods to handle async tasks. Some interesting uses involve chaining tasks, combining multiple futures using allof and anyof. I will try to implement this
   as it might be a way of handling multiple tasks (like in our scraper for loop) and once all of them are done, signal the method to finally return the whole thing. 



