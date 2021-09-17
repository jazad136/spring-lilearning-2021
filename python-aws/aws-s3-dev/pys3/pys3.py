# -*- coding: utf-8 -*-

import os
from botocore.exceptions import ClientError
ACCESS_KEY = 'AWS_ACCESS_KEY_ID'
SECRET_KEY = 'AWS_SECRET_ACCESS_KEY'
PRI_BUCKET_NAME = 'jaslil'
TRANSIENT_BUCKET_NAME = 'jaslil2'
f1 = 'lil1.txt'
f2 = 'lil2.txt'
f3 = 'lil3.txt'
base = os.path.join('C:', 'Users', "jsaddle", "code", "repos", "plain", "AWS_Moley_exercise2")
DIR = os.path.join(base, 'aws-s3-dev', 'pys3')
DOWN_DIR = os.path.join(base, 'aws-s3-dev', 's3alt')
#DIR = 'C:/Users/jsaddle/code/repos/plain/AWS_Moley_exercise2/aws-s3-dev/pys3'
#DOWN_DIR = 'C:/Users/jsaddle/code/repos/plain/AWS_Moley_exercise2/aws-s3-dev/s3alt'

def main():
    '''Entry point'''
    import boto3
    access = os.getenv(ACCESS_KEY)
    secret = os.getenv(SECRET_KEY)
    s3 = boto3.resource('s3', aws_access_key_id=access, aws_secret_acccess_key=secret)
    create_bucket(TRANSIENT_BUCKET_NAME, s3)
    
def create_bucket(name, s3):
    try:
        bucket = s3.create_bucket(bucket=name)
    except ClientError as ce:
        print('error', ce)
        
if __name__ == '__main__':
    main()