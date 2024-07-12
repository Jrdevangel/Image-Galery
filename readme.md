<hr>
<br>

## USER STORY

- **Title** Image Gallery
- **As** Image Gallery platform user
- **I want** to be able to manage images easily and efficiently
- **To** be able to access a list of favorite images in an organized and personalized way

#### Criteria of acceptance:

***Add Images***

- ***Given:*** I am a user with creation permissions,
- ***When:*** I navigate to the Image Gallery page,
- ***So:*** I can fill out a form with the title and description and the image url,
- ***And:*** When you submit the form, the image is added to the database and appears in the favourites images list.

<hr>

<br>

## PSEUDOCODE

#### Image Gallery Controller
    Define function addNewImage with POST method at the path "/images"
    Receive Image object in the request body (RequestBody)

    Call addNewImage function of imageGalleryService passing the Image object
    Return the result of the service call

#### Image Gallery Service

    Define function addNewImage in imageGalleryService
    Receive Image object as a parameter

    Call save function of repository iImageGalleryRepository passing the Image object
    Return the result of the repository call
<hr>

<br>