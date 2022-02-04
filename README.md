# usluarda60 - dev.akademi2022

Dev Akademi Maratonu kapsamında verilen argümanlarla Spring ile bir REST Api oluşturmayı hedefledim.

# Kullanılan Teknolojiler
Bu projede geliştirme yapılabilmesi için gereken kurulumlar:

* [Eclipse IDE for Enterprise Java Developers](https://www.eclipse.org/downloads/packages/release/2020-09/r/eclipse-ide-enterprise-java-developers) (Kurulumu tamamladıktan sonra Eclipse Marketplace'den Spring Tool 4 indirilmelidir.)
* [MySQL Community (Version: 8.0.21)](https://dev.mysql.com/downloads/installer/)
* [Lombok](https://projectlombok.org/setup/eclipse)
* [JAVA Version 1.8](https://www.java.com/download/ie_manual.jsp)
* SonarLint

Endpointlerimi denemek için Postman aracını kullandım.

# Projenin Çalıştırılması

* Proje main branchine geçmediği için öncelike git checkout feature_DevAkademi2022_AU ile projenin bulunduğu branche geçin. 
* Eclipse IDE üzerinden File → Import seçeneğinden "Existing Maven Projects"i seçin. Root Directory olarak X:\....\usluarda60-dev-akademi2022\sahibinden-devakademi-demo'yu seçin. "Projects" listesinde "pom.xml com.sahibinden.devakademi.demo:0.0.1-SNAPSHOT:jar"ın seçili olduğundan emin olup işlemi tamamlayın.
* Project Explorer'da sahibinden-devakademi-demo/src/main/resources içinde bulunan application.properties dosyasında bulunan "spring.datasource.username" ve "spring.datasource.password" ayarlarını kendi MySQL bağlantı ayarlarınızdaki gibi güncelleyin. 
* MySQL Workbench üzerinden "devakadmi_demo" adında bir database oluşturun.
* Project Explorer'da projeye sağ tıklayarak Run As → Spring Boot App'i seçerek projeyi çalıştırabilirsiniz.

# Eklemelerim: 

* Her bir model için CRUD operasyonlarını tamamlamayı hedefledim.
* Verilen argümanları model olarak oluşturuken bazı modeller arasında ilişkiler kurdum.

Veri tabanı tablosu:
![dev_akademi_er](https://user-images.githubusercontent.com/53192718/152583389-a7e56dee-7976-48b9-a9b0-8b631aea6254.png)

Veri tabanındaki değişiklikler sonrasında endpointlere gitmesi gereken istek örneklerini readme'nin sonuna ekledim.

# Eksiklererim:

* Projeye başlamadan önce her bir method bittikten sonra ilgili metodun birim testini yazmayı hedefliyordum, fakat bu şekilde ilerlemedim. Gerekli CRUD methodlarını yazdıktan sonra birim testleri yazmaya başladım. Ana hedefim hem tüm katmanlar için gereken tüm birim testleri tamamlamaktı.

Projenin şu anki haliyle CodeCoverage görseldeki gibidir:

![dev_akadem_code_coverage](https://user-images.githubusercontent.com/53192718/152582536-f53b2bca-bf32-4594-b416-32205f92f600.png)

* Birim testler dışında kod içerisinde bazı iyileştirmelerin gerektiğine eminim.
* Yine projeye başlamadan önceki hedefim her adımı düzenli olarak commitlemekti, açıkçası bu hedefimi gerçekleştirmek maraton sırasında aklıma gelmedi.


# Veri tabanındaki değişiklikler sonrasında endpointlere gitmesi gereken istek örnekleri:

## User
### Get Page By Page
```
    http://localhost:8080/v1/api/access-logs?pageNo=0
    http://localhost:8080/v1/api/users?pageNo=1
    http://localhost:8080/v1/api/classifieds?pageNo=2
    http://localhost:8080/v1/api/payment-details?pageNo=3
```
### Get By Id
```
    http://localhost:8080/v1/api/access-logs/56
    http://localhost:8080/v1/api/users/5
    http://localhost:8080/v1/api/classifieds/9
    http://localhost:8080/v1/api/payment-details/5
```
### Update
```
    http://localhost:8080/v1/api/classifieds/update
```
#### Classified JSON
```
    {
    "data": 
    {
      "id": 1,
      "user": {
          "id": 6
      },      
      "title": "Impetus Omnesque Convenire Posse Habeo",
      "description": "deseruisse interdum facilis lorem ocurreret",
      "currency": "USD",
      "price": 0.1467789001122335,
      "status": "ACTIVE",
      "createdDate": 1614470489958,
      "publishedBy": "INDIVIDUAL",
      "city": "Parrot",
      "category": "REAL_ESTATE",
      "classifiedAttributes": [
        {
          "id": 563650,
          "classifiedId": 1,
          "attributeName": "Metre Kare",
          "attributeValue": "75"
        },
        {
          "id": 566084,
          "classifiedId": 1,
          "attributeName": "Buldunuğu Kat",
          "attributeValue": "9"
        },
        {
          "id": 566085,
          "classifiedId": 1,
          "attributeName": "Bina Yaşı",
          "attributeValue": "7"
        }
      ],
      "coordinate": {
        "x": 41.50729926961459,
        "y": 39.170931647096054
      }
    }
}
```
### Delete
```
    http://localhost:8080/v1/api/users/access-logs/1,2,6,9
    http://localhost:8080/v1/api/users/classifieds/1,2,6,9
    http://localhost:8080/v1/api/users/payment-details/1,2,6,9
    http://localhost:8080/v1/api/users/delete/1,2,6,9
```

### Post
#### User
```
    http://localhost:8080/v1/api/users/add

```

##### User Json: 
```
{
  "data": [
    {
      "id": 59,
      "firstName": "Lauri",
      "lastName": "Robertson",
      "status": "PASSIVE"
    },
    {
      "id": 60,
      "firstName": "Concepcion",
      "lastName": "Foreman",
      "status": "ACTIVE"
    },
    {
      "id": 61,
      "firstName": "Caitlin",
      "lastName": "Buckner",
      "status": "PASSIVE"
    },
    {
      "id": 62,
      "firstName": "Adrienne",
      "lastName": "Pope",
      "status": "PASSIVE"
    },
    {
      "id": 63,
      "firstName": "Deirdre",
      "lastName": "Lee",
      "status": "ACTIVE"
    },
    {
      "id": 64,
      "firstName": "Rae",
      "lastName": "Zimmerman",
      "status": "PASSIVE"
    },
    {
      "id": 65,
      "firstName": "Fernando",
      "lastName": "Hess",
      "status": "PASSIVE"
    },
    {
      "id": 66,
      "firstName": "Sallie",
      "lastName": "Hess",
      "status": "PASSIVE"
    },
    {
      "id": 67,
      "firstName": "Nannie",
      "lastName": "Clark",
      "status": "PASSIVE"
    },
    {
      "id": 68,
      "firstName": "Eugene",
      "lastName": "Ray",
      "status": "PASSIVE"
    },
    {
      "id": 69,
      "firstName": "Shelton",
      "lastName": "Bell",
      "status": "ACTIVE"
    },
    {
      "id": 70,
      "firstName": "Miles",
      "lastName": "Kerr",
      "status": "PASSIVE"
    },
    {
      "id": 71,
      "firstName": "Archie",
      "lastName": "Blackburn",
      "status": "PASSIVE"
    },
    {
      "id": 72,
      "firstName": "Juan",
      "lastName": "Farmer",
      "status": "PASSIVE"
    },
    {
      "id": 73,
      "firstName": "Nickolas",
      "lastName": "Wilkinson",
      "status": "ACTIVE"
    },
    {
      "id": 74,
      "firstName": "Marty",
      "lastName": "Gardner",
      "status": "PASSIVE"
    },
    {
      "id": 75,
      "firstName": "Jaclyn",
      "lastName": "Erickson",
      "status": "ACTIVE"
    },
    {
      "id": 76,
      "firstName": "Polly",
      "lastName": "Rutledge",
      "status": "ACTIVE"
    },
    {
      "id": 77,
      "firstName": "Vincent",
      "lastName": "Vazquez",
      "status": "PASSIVE"
    },
    {
      "id": 78,
      "firstName": "Gale",
      "lastName": "Morris",
      "status": "ACTIVE"
    }
  ]
}
```

#### Classified
```
    http://localhost:8080/v1/api/classifieds/add

```

#### User Json: 
```
{
  "data": [
    {
      "id": 1,
      "user": {
          "id": 4
      },      
      "title": "Impetus Omnesque Convenire Posse Habeo",
      "description": "deseruisse interdum facilis lorem ocurreret",
      "currency": "TRY",
      "price": 0.1467789001122335,
      "status": "ACTIVE",
      "createdDate": 1614470489958,
      "publishedBy": "INDIVIDUAL",
      "city": "Parrot",
      "category": "REAL_ESTATE",
      "classifiedAttributes": [
        {
          "id": 563650,
          "classifiedId": 1,
          "attributeName": "Metre Kare",
          "attributeValue": "75"
        },
        {
          "id": 566084,
          "classifiedId": 1,
          "attributeName": "Buldunuğu Kat",
          "attributeValue": "9"
        },
        {
          "id": 566085,
          "classifiedId": 1,
          "attributeName": "Bina Yaşı",
          "attributeValue": "7"
        }
      ],
      "coordinate": {
        "x": 41.50729926961459,
        "y": 39.170931647096054
      }
    },
    {
      "id": 2,
   "user": {
          "id": 4
      },     
      "title": "Interpretaris Debet Percipit a Consetetur",
      "description": "verear dolorem eos possit explicari",
      "currency": "TRY",
      "price": 0.6253049008604564,
      "status": "WAITING_FOR_APPROVAL",
      "createdDate": 1630842886962,
      "publishedBy": "INDIVIDUAL",
      "city": "Parrot",
      "category": "REAL_ESTATE",
      "classifiedAttributes": [
        {
          "id": 566086,
          "classifiedId": 2,
          "attributeName": "Metre Kare",
          "attributeValue": "123"
        },
        {
          "id": 566087,
          "classifiedId": 2,
          "attributeName": "Buldunuğu Kat",
          "attributeValue": "Giriş Katı"
        },
        {
          "id": 566088,
          "classifiedId": 2,
          "attributeName": "Bina Yaşı",
          "attributeValue": "18"
        }
      ],
      "coordinate": {
        "x": 40.31753003037345,
        "y": 29.314717376297043
      }
    },
    {
      "id": 3,
   "user": {
          "id": 4
      },      "title": "Volutpat Tritani Libero Expetenda Commune",
      "description": "graece commune congue pertinax dolorum",
      "currency": "TRY",
      "price": 0.22439955106621778,
      "status": "WAITING_FOR_APPROVAL",
      "createdDate": 1617126080627,
      "publishedBy": "CORPORATE",
      "city": "Carpentersville",
      "category": "REAL_ESTATE",
      "classifiedAttributes": [
        {
          "id": 566089,
          "classifiedId": 3,
          "attributeName": "Metre Kare",
          "attributeValue": "130"
        },
        {
          "id": 566090,
          "classifiedId": 3,
          "attributeName": "Buldunuğu Kat",
          "attributeValue": "9"
        },
        {
          "id": 566091,
          "classifiedId": 3,
          "attributeName": "Bina Yaşı",
          "attributeValue": "15"
        }
      ],
      "coordinate": {
        "x": 36.537917279233234,
        "y": 33.13858380804696
      }
    },
    {
      "id": 4,
   "user": {
          "id": 4
      },      "title": "Consectetur Mnesarchum Accusata sea Novum",
      "description": "condimentum felis vix repudiare praesent",
      "currency": "TRY",
      "price": 0.6333408155630378,
      "status": "PASSIVE",
      "createdDate": 1631117786519,
      "publishedBy": "INDIVIDUAL",
      "city": "Soda Bay",
      "category": "REAL_ESTATE",
      "classifiedAttributes": [
        {
          "id": 566092,
          "classifiedId": 4,
          "attributeName": "Metre Kare",
          "attributeValue": "86"
        },
        {
          "id": 566093,
          "classifiedId": 4,
          "attributeName": "Buldunuğu Kat",
          "attributeValue": "3"
        },
        {
          "id": 566094,
          "classifiedId": 4,
          "attributeName": "Bina Yaşı",
          "attributeValue": "20"
        }
      ],
      "coordinate": {
        "x": 36.78254858582918,
        "y": 36.6594153171908
      }
    },
    {
      "id": 5,
   "user": {
          "id": 4
      },      "title": "Sadipscing Iisque Verear Indoctum Magnis",
      "description": "eget volutpat quisque equidem tritani",
      "currency": "TRY",
      "price": 0.18063598952487836,
      "status": "ACTIVE",
      "createdDate": 1615628836351,
      "publishedBy": "INDIVIDUAL",
      "city": "Mayberry",
      "category": "REAL_ESTATE",
      "classifiedAttributes": [
        {
          "id": 566095,
          "classifiedId": 5,
          "attributeName": "Metre Kare",
          "attributeValue": "26"
        },
        {
          "id": 566096,
          "classifiedId": 5,
          "attributeName": "Buldunuğu Kat",
          "attributeValue": "Kot 1"
        },
        {
          "id": 566097,
          "classifiedId": 5,
          "attributeName": "Bina Yaşı",
          "attributeValue": "3"
        }
      ],
      "coordinate": {
        "x": 40.01136139866948,
        "y": 28.175829941798135
      }
    },
    {
      "id": 6,
   "user": {
          "id": 4
      },      "title": "Tacimates Consequat Posse Saperet Melius",
      "description": "pharetra maiorum veritus mel disputationi",
      "currency": "TRY",
      "price": 0.39623643845866063,
      "status": "WAITING_FOR_APPROVAL",
      "createdDate": 1623005520759,
      "publishedBy": "CORPORATE",
      "city": "Cardtown",
      "category": "SHOPPING",
      "classifiedAttributes": [
        {
          "id": 566098,
          "classifiedId": 6,
          "attributeName": "Boyut",
          "attributeValue": "100x600"
        },
        {
          "id": 566099,
          "classifiedId": 6,
          "attributeName": "Renk",
          "attributeValue": "Siyah"
        },
        {
          "id": 566100,
          "classifiedId": 6,
          "attributeName": "Kullanım Durumu",
          "attributeValue": "İkinci El"
        }
      ],
      "coordinate": {
        "x": 41.51465273798124,
        "y": 39.91286911290654
      }
    },
    {
      "id": 7,
   "user": {
          "id": 4
      },      "title": "Ut eu Postea Persecuti Cras",
      "description": "oratio pulvinar sale etiam iriure",
      "currency": "TRY",
      "price": 0.24581743359521135,
      "status": "ACTIVE",
      "createdDate": 1617858920894,
      "publishedBy": "CORPORATE",
      "city": "Welcome Corner",
      "category": "SHOPPING",
      "classifiedAttributes": [
        {
          "id": 566101,
          "classifiedId": 7,
          "attributeName": "Boyut",
          "attributeValue": "60x50"
        },
        {
          "id": 566102,
          "classifiedId": 7,
          "attributeName": "Renk",
          "attributeValue": "Kırmızı"
        },
        {
          "id": 566103,
          "classifiedId": 7,
          "attributeName": "Kullanım Durumu",
          "attributeValue": "İkinci El"
        }
      ],
      "coordinate": {
        "x": 41.51083333461906,
        "y": 32.49386710819601
      }
    },
    {
      "id": 8,
   "user": {
          "id": 4
      },      "title": "Est Legimus dis Dico Oporteat",
      "description": "venenatis ubique vivamus labores duo",
      "currency": "TRY",
      "price": 0.8849083426861205,
      "status": "ACTIVE",
      "createdDate": 1639725018404,
      "publishedBy": "CORPORATE",
      "city": "Note",
      "category": "SHOPPING",
      "classifiedAttributes": [
        {
          "id": 566104,
          "classifiedId": 8,
          "attributeName": "Boyut",
          "attributeValue": "20x10"
        },
        {
          "id": 566105,
          "classifiedId": 8,
          "attributeName": "Renk",
          "attributeValue": "Siyah"
        },
        {
          "id": 566106,
          "classifiedId": 8,
          "attributeName": "Kullanım Durumu",
          "attributeValue": "İkinci El"
        }
      ],
      "coordinate": {
        "x": 39.75789011423085,
        "y": 41.58042571570326
      }
    },
    {
      "id": 9,
   "user": {
          "id": 4
      },      "title": "Aliquam Unum Idque Iriure Aliquam",
      "description": "verear oratio deserunt affert possim",
      "currency": "TRY",
      "price": 0.8517283950617284,
      "status": "PASSIVE",
      "createdDate": 1638589791198,
      "publishedBy": "CORPORATE",
      "city": "Metacalf",
      "category": "SHOPPING",
      "classifiedAttributes": [
        {
          "id": 566107,
          "classifiedId": 9,
          "attributeName": "Boyut",
          "attributeValue": "20x60"
        },
        {
          "id": 566108,
          "classifiedId": 9,
          "attributeName": "Renk",
          "attributeValue": "Gri"
        },
        {
          "id": 566109,
          "classifiedId": 9,
          "attributeName": "Kullanım Durumu",
          "attributeValue": "İkinci El"
        }
      ],
      "coordinate": {
        "x": 38.58379909092473,
        "y": 40.88040443885842
      }
    },
    {
      "id": 10,
   "user": {
          "id": 4
      },      "title": "Fugit Instructior Libris Ultrices Propriae",
      "description": "unum ea suspendisse mutat explicari",
      "currency": "TRY",
      "price": 0.4587355031799476,
      "status": "WAITING_FOR_APPROVAL",
      "createdDate": 1625143828032,
      "publishedBy": "CORPORATE",
      "city": "Rurojo",
      "category": "SHOPPING",
      "classifiedAttributes": [
        {
          "id": 566110,
          "classifiedId": 10,
          "attributeName": "Boyut",
          "attributeValue": "20x60"
        },
        {
          "id": 566111,
          "classifiedId": 10,
          "attributeName": "Renk",
          "attributeValue": "Gri"
        },
        {
          "id": 566112,
          "classifiedId": 10,
          "attributeName": "Kullanım Durumu",
          "attributeValue": "İkinci El"
        }
      ],
      "coordinate": {
        "x": 38.97412289126997,
        "y": 41.9753230073388
      }
    }
  ]
}
```

#### Payment Detail
```
    http://localhost:8080/v1/api/payment-details/add

```

#### Payment Detail Json: 
```
  {
  "data": [
    {
      "id": 561001,
      "classified": {
        "id": 1
      },
      "createdDate": 1614470489958,
      "amount": 150,
      "discount": 0
    },
    {
      "id": 561002,
       "classified": {
        "id": 2
      },
      "createdDate": 1630842886962,
      "amount": 150,
      "discount": 0
    },
    {
      "id": 561003,
       "classified": {
        "id": 4
      },
      "createdDate": 1631117786519,
      "amount": 150,
      "discount": 0
    },
    {
      "id": 561004,
       "classified": {
        "id": 5
      },
      "createdDate": 1615628836351,
      "amount": 150,
      "discount": 0
    },
    {
      "id": 561005,
      "classified": {
        "id": 6
      },
      "createdDate": 1617858920894,
      "amount": 16.6,
      "discount": 17
    },
    {
      "id": 561006,
       "classified": {
        "id": 8
      },
      "createdDate": 1639725018404,
      "amount": 20,
      "discount": 0
    },
    {
      "id": 561007,
       "classified": {
        "id": 10
      },
      "createdDate": 1625143828032,
      "amount": 17.2,
      "discount": 14
    },
    {
      "id": 561008,
       "classified": {
        "id": 11
      },
      "createdDate": 1613656043614,
      "amount": 150,
      "discount": 0
    },
    {
      "id": 561009,
       "classified": {
        "id": 13
      },
      "createdDate": 1642947046969,
      "amount": 150,
      "discount": 0
    },
    {
      "id": 561010,
       "classified": {
        "id": 14
      },
      "createdDate": 1624721567755,
      "amount": 20,
      "discount": 0
    },
    {
      "id": 561011,
       "classified": {
        "id": 16
      },
      "createdDate": 1625624316374,
      "amount": 20,
      "discount": 0
    },
    {
      "id": 561012,
       "classified": {
        "id": 17
      },
      "createdDate": 1629754156580,
      "amount": 20,
      "discount": 0
    },
    {
      "id": 561013,
       "classified": {
        "id": 19
      },
      "createdDate": 1642267642451,
      "amount": 150,
      "discount": 0
    },
    {
      "id": 561014,
       "classified": {
        "id": 20
      },
      "createdDate": 1617110998592,
      "amount": 150,
      "discount": 0
    },
    {
      "id": 561015,
      "classified": {
        "id": 20
      },
      "createdDate": 1614640090040,
      "amount": 150,
      "discount": 0
    },
    {
      "id": 561016,
       "classified": {
        "id": 20
      },
      "createdDate": 1624704389088,
      "amount": 150,
      "discount": 0
    },
    {
      "id": 561017,
       "classified": {
        "id": 20
      },
      "createdDate": 1625424125099,
      "amount": 20,
      "discount": 0
    },
    {
      "id": 561018,
       "classified": {
        "id": 20
      },
      "createdDate": 1611593596774,
      "amount": 20,
      "discount": 0
    },
    {
      "id": 561019,
       "classified": {
        "id": 20
      },
      "createdDate": 1642283821545,
      "amount": 20,
      "discount": 0
    },
    {
      "id": 561020,
       "classified": {
        "id": 20
      },
      "createdDate": 1617965048229,
      "amount": 20,
      "discount": 0
    }
  ]
}
```

#### Access Log
```
    http://localhost:8080/v1/api/access-logs/add

```

#### Access Log Json: 
```
{
  "data": [
    {
      "id": 59,
      "firstName": "Lauri",
      "lastName": "Robertson",
      "status": "PASSIVE",
      "user": {
          "id": 4
      }
    },
    {
      "id": 60,
      "firstName": "Concepcion",
      "lastName": "Foreman",
      "status": "ACTIVE",
      "user": {
          "id": 5
      }
    },
    {
      "id": 61,
      "firstName": "Caitlin",
      "lastName": "Buckner",
      "status": "PASSIVE",
      "user": {
          "id": 5
      }
    },
    {
      "id": 62,
      "firstName": "Adrienne",
      "lastName": "Pope",
      "status": "PASSIVE",
      "user": {
          "id": 10
      }
    },
    {
      "id": 63,
      "firstName": "Deirdre",
      "lastName": "Lee",
      "status": "ACTIVE",
      "user": {
          "id": 11
      }
    },
    {
      "id": 64,
      "firstName": "Rae",
      "lastName": "Zimmerman",
      "status": "PASSIVE",
      "user": {
          "id": 5
      }
    },
    {
      "id": 65,
      "firstName": "Fernando",
      "lastName": "Hess",
      "status": "PASSIVE",
      "user": {
          "id": 4
      }
    },
    {
      "id": 66,
      "firstName": "Sallie",
      "lastName": "Hess",
      "status": "PASSIVE",
      "user": {
          "id": 4
      }
    },
    {
      "id": 67,
      "firstName": "Nannie",
      "lastName": "Clark",
      "status": "PASSIVE",
      "user": {
          "id": 4
      }
    },
    {
      "id": 68,
      "firstName": "Eugene",
      "lastName": "Ray",
      "status": "PASSIVE",
      "user": {
          "id": 4
      }
    }
  ]
}
```

