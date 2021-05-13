# Scrapped
Scraping financial news related to India in the last year and performing sentiment analysis on them.

## Contents
* Prerequisites
* Data Gathering
* Pre Processing
* Sentiment analysis

## Prerequisites
* Eclipse
* Maven
* Jdk 8 or above
* Selenium framework
* Chrome webdriver

## Data gathering

As most of the News site don’t have an API integration for information exchanging, we have to go for automation and for that we use the Selenium framework.
We automate the web browser to do the following things-

* Go to https://economictimes.indiatimes.com/news/economy/finance which has the latest financial news related to India.
*	The show more button at the end of the page loaded 10 entries at every click so we automate it to be clicked till we reach an approximate date range.
*	Now that we have all the news in one page all we have to do is look at the HTML and find out which tags are enclosing the headlines and their respective dates.
*	Selenium’s inbuilt methods make it really easy to get all the elements. We use the `findElements(By.tagname())` to get all the headlines and store them in a list and their respective dates.
*	Now we make an Excel workbook and write the data into it. We need `apache-poi` jar files do this and maven makes it really easy to manage these dependencies. 

## Pre-Processing

We have the dataset with us but to get higher accuracy while doing the analysis we need to clean the data further. We remove any stopwords which don’t contribute to the sentiment.
We remove extra spaces,non-numeric,non-alphabetic characters and then break down sentences into tokens and then perform lemmatization(Lemmatization maps a word to its lemma (dictionary form)).
Now we can perform the sentiment Analysis on the dataset.

## Sentiment Analysis

As we’re working with Java there are only a few open source libraries that help us to perform NLP whereas python is clearly the best choice to perform NLP. We use Standford’s Core-NLP to help us through this problem.
All we have to do is add the jar file in our pom.xml file and we’re ready to go. 
We read data from our previously made xlxs file and pass each sentence which is preprocessed and made into a document which can be iterated.
Now we just use the sentiment method to get the sentiment value and return it which is written to the xlxs file
