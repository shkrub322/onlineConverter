#!/usr/bin/env python3
# -*- coding: utf-8 -*-
import requests
from bs4 import BeautifulSoup
import json


def get_html(url):
    r = requests.get(url)
    return r.text


def main():
    html = get_html('https://myfin.by/currency/minsk')
    cities = get_cities(html)
    departments_for_write = []

    for city in cities:
        html = get_html('https://myfin.by/currency/' + city['ref'])
        departments = get_departments(html, city['city'])
        for department in departments:
            departments_for_write.append(department)
    write(departments_for_write, '/home/shkrub322/IdeaProjects/coursework/onlineConverter/src/main/java/com/shkrub/onlineConverter/parser/json/data.json')
    print("Success")


def write(data, filename):
    file = open(filename, 'w')
    json.dump(data, file, indent=4, ensure_ascii=False)
    file.close()


def get_cities(html):
    soup = BeautifulSoup(html, 'html5lib')
    divs = soup.findAll('div', class_='item-list col-md-2 col-sm-3 col-xs-12')
    regions = []
    cities = []

    for div in divs:
        region = div.find('div').contents[0]
        regions.append({
            'region': region
        })
        lies = div.findAll('li')
        for li in lies:
            cities.append({
                'region': region,
                'city': li.find('a').contents[0],
                'ref': li.find('a')['data-slug']
            })

    write(regions, '/home/shkrub322/IdeaProjects/coursework/onlineConverter/src/main/java/com/shkrub/onlineConverter/parser/json/regions.json')
    write(cities, '/home/shkrub322/IdeaProjects/coursework/onlineConverter/src/main/java/com/shkrub/onlineConverter/parser/json/cities.json')
    return cities


def get_departments(html, city):
    soup = BeautifulSoup(html, 'html5lib')
    tables = soup.findAll('table', class_='')
    departments = []
    for i in range(1, len(tables)):
        bank = tables[i].find('th').contents[0][10:]
        rows = tables[i].findAll('tr', class_='currency_row_1')
        for row in rows:
            tds = row.findAll('td')
            departments.append({
                'name': row.find('a').contents[0],
                'address': row.find('div', class_='address').contents[0],
                'bank': bank,
                'city': city,
                'rate': set_rates(tds)
            })
    return departments


def set_rates(rates):
    dict = [
        {"USD": rates[1].contents[0],
         'operation': 'покупка'},
        {"USD": rates[2].contents[0],
         'operation': 'продажа'},
        {"EUR": rates[3].contents[0],
         'operation': 'покупка'},
        {"EUR": rates[4].contents[0],
         'operation': 'продажа'},
        {"RUB": rates[5].contents[0],
         'operation': 'покупка'},
        {"RUB": rates[6].contents[0],
         'operation': 'продажа'},
        {"EUR/USD": rates[7].contents[0],
         'operation': 'покупка'},
        {"EUR/USD": rates[8].contents[0],
         'operation': 'продажа'}
    ]
    return dict


main()
