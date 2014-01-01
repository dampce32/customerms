var CMS={};
//模版文件路径
//编号名称
CMS.codeNameTemplatePath = 'dict/codeName.xls';
CMS.teachMaterialTemplatePath = 'dict/teachMaterial.xls';
CMS.researchAwardTemplateBasePath = 'researchAward/';
CMS.projectTemplatePath = 'research/project.xls';
CMS.bookTemplatePath = 'research/book.xls';
CMS.paperTemplatePath = 'research/paper.xls';
CMS.prizeResultTemplatePath = 'research/prizeResult.xls';
CMS.appraisalTemplatePath = 'research/appraisal.xls';
CMS.teaPhotoPath = 'upload/image/teaPhoto';
CMS.patentTemplatePath =  'research/patent.xls';
CMS.courseTemplatePath =  'dict/course.xls';
CMS.programsCourseTemplatePath =  'programs/programsCourse.xls';
CMS.studentTemplatePath = 'student/student.xls';
CMS.externalTeaTemplatePath = 'personnel/externalTea.xls';
CMS.textBookTemplatePath = 'dict/textBook.xls';
CMS.softwareTemplatePath = 'research/software.xls';

//格式化相对学期
CMS.semesterArray = new Array('','一','二','三','四','五','六','七','八');
CMS.RelativaSemesterFormatter = function(semesterStr){
	if(semesterStr==null||semesterStr==''){
		return;
	}
	var semesterList = semesterStr.split(",");
	var index = parseInt(semesterList[0]);
	var result = '第'+CMS.semesterArray[index];
	if(semesterList.length>1){
		for(var i=1;i<semesterList.length;i++){
			index = parseInt(semesterList[i]);
			result += '、'+CMS.semesterArray[index];
		}
	}
	result += '学期';
	return result;
};

//当前操作员登录数据
CMS.currTeacher = null;
CMS.getCurrTeacher = function(){
	if(CMS.currTeacher==null){
		var url = 'system/getCurrTeacher.do';
		var result = syncCallService(url,null);
		if(result.isSuccess){
			CMS.currTeacher = result.data;
		}else{
			$.messager.alert('提示',result.message,'error');
		}
	}
	return CMS.currTeacher;
};
//在职状态
CMS.TeaWorkingStatusId = null;
CMS.getTeaWorkingStatusId = function(){
	if(CMS.TeaWorkingStatusId==null){
		var url = 'dictWorkPost/getWorkingStatusTeaStatus.do';
		var result = syncCallService(url);
		if(result.isSuccess){
			var data = result.data;
			CMS.TeaWorkingStatusId = data.teaStatusId;
		}else{
			$.messager.alert('提示',result.message,'error');
		}
	}
	return CMS.TeaWorkingStatusId;
};
//民族
CMS.NationList = null;
CMS.getNationList = function(){
	if(CMS.NationList==null){
		var url = 'dictBase/queryComboboxNation.do';
		CMS.NationList = syncCallService(url);
	}
	return CMS.NationList;
};
//血型
CMS.BloodGroupList = null;
CMS.getBloodGroupList = function(){
	if(CMS.BloodGroupList==null){
		var url = 'dictBase/queryComboboxBloodGroup.do';
		CMS.BloodGroupList = syncCallService(url);
	}
	return CMS.BloodGroupList;
};
//港澳台侨
CMS.CompatriotTypeList = null;
CMS.getCompatriotTypeList = function(){
	if(CMS.CompatriotTypeList==null){
		var url = 'dictBase/queryComboboxCompatriotType.do';
		CMS.CompatriotTypeList = syncCallService(url);
	}
	return CMS.CompatriotTypeList;
};
//个人成份
CMS.IndividualStatusList = null;
CMS.getIndividualStatusList = function(){
	if(CMS.IndividualStatusList==null){
		var url = 'dictBase/queryComboboxIndividualStatus.do';
		CMS.IndividualStatusList = syncCallService(url);
	}
	return CMS.IndividualStatusList;
};
//婚姻状况
CMS.MaritalStatusList = null;
CMS.getMaritalStatusList = function(){
	if(CMS.MaritalStatusList==null){
		var url = 'dictBase/queryComboboxMaritalStatus.do';
		CMS.MaritalStatusList = syncCallService(url);
	}
	return CMS.MaritalStatusList;
};
//健康状况
CMS.HealthList = null;
CMS.getHealthList = function(){
	if(CMS.HealthList==null){
		var url = 'dictBase/queryComboboxHealth.do';
		CMS.HealthList = syncCallService(url);
	}
	return CMS.HealthList;
};
//教师类型
CMS.TeacherTypeList = null;
CMS.getTeacherTypeList = function(){
	if(CMS.TeacherTypeList==null){
		var url = 'dictWorkPost/queryComboboxTeacherType.do';
		CMS.TeacherTypeList = syncCallService(url);
	}
	return CMS.TeacherTypeList;
};
//国家
CMS.CountryList = null;
CMS.getCountryList = function(){
	if(CMS.CountryList==null){
		var url = 'dictBase/queryComboboxCountry.do';
		CMS.CountryList = syncCallService(url);
	}
	return CMS.CountryList;
};
//关系
CMS.RelationshipList = null;
CMS.getRelationshipList = function(){
	if(CMS.RelationshipList==null){
		var url = 'dictBase/queryComboboxRelationship.do';
		CMS.RelationshipList = syncCallService(url);
	}
	return CMS.RelationshipList;
};
//户口性质
CMS.CensusStatusList = null;
CMS.getCensusStatusList = function(){
	if(CMS.CensusStatusList==null){
		var url = 'dictBase/queryComboboxCensusStatus.do';
		CMS.CensusStatusList = syncCallService(url);
	}
	return CMS.CensusStatusList;
};
//家庭出生
CMS.FamilyBirthList = null;
CMS.getFamilyBirthList = function(){
	if(CMS.FamilyBirthList==null){
		var url = 'dictBase/queryComboboxFamilyBirth.do';
		CMS.FamilyBirthList = syncCallService(url);
	}
	return CMS.FamilyBirthList;
};
//文化程度
CMS.EducationStatusList = null;
CMS.getEducationStatusList = function(){
	if(CMS.EducationStatusList==null){
		var url = 'dictBase/queryComboboxEducationStatus.do';
		CMS.EducationStatusList = syncCallService(url);
	}
	return CMS.EducationStatusList;
};

//岗位类型
CMS.PostTypeList = null;
CMS.getPostTypeList = function(){
	if(CMS.PostTypeList==null){
		var url = 'dictWorkPost/queryComboboxPostType.do';
		CMS.PostTypeList = syncCallService(url);
	}
	return CMS.PostTypeList;
};
//调入方式
CMS.WorkTypeList = null;
CMS.getWorkTypeList = function(){
	if(CMS.WorkTypeList==null){
		var url = 'dictWorkPost/queryComboboxWorkType.do';
		CMS.WorkTypeList = syncCallService(url);
	}
	return CMS.WorkTypeList;
};
//从事工作类型
CMS.CallInTypeList = null;
CMS.getCallInTypeList = function(){
	if(CMS.CallInTypeList==null){
		var url = 'dictWorkPost/queryComboboxCallInType.do';
		CMS.CallInTypeList = syncCallService(url);
	}
	return CMS.CallInTypeList;
};
//教师状态
CMS.TeaStatusList = null;
CMS.getTeaStatusList = function(){
	if(CMS.TeaStatusList==null){
		var url = 'dictWorkPost/queryComboboxTeaStatus.do';
		CMS.TeaStatusList = syncCallService(url);
	}
	return CMS.TeaStatusList;
};
//政治面貌
CMS.PoliticalStatusList = null;
CMS.getPoliticalStatusList = function(){
	if(CMS.PoliticalStatusList==null){
		var url = 'dictBase/queryComboboxPoliticalStatus.do';
		CMS.PoliticalStatusList = syncCallService(url);
	}
	return CMS.PoliticalStatusList;
};
//职务级别
CMS.PostLevelList = null;
CMS.getPostLevelList = function(){
	if(CMS.PostLevelList==null){
		var url = 'dictPosition/queryComboboxPostLevel.do';
		CMS.PostLevelList = syncCallService(url);
	}
	return CMS.PostLevelList;
};
//职务类别
CMS.PositionTypeList = null;
CMS.getPositionTypeList = function(){
	if(CMS.PositionTypeList==null){
		var url = 'dictPosition/queryComboboxPositionType.do';
		CMS.PositionTypeList = syncCallService(url);
	}
	return CMS.PositionTypeList;
};
//职务变动类型
CMS.PositionChangeTypeList = null;
CMS.getPositionChangeTypeList = function(){
	if(CMS.PositionChangeTypeList==null){
		var url = 'dictPosition/queryComboboxPositionChangeType.do';
		CMS.PositionChangeTypeList = syncCallService(url);
	}
	return CMS.PositionChangeTypeList;
};
//免职方式
CMS.RemovalWayList = null;
CMS.getRemovalWayList = function(){
	if(CMS.RemovalWayList==null){
		var url = 'dictPosition/queryComboboxRemovalWay.do';
		CMS.RemovalWayList = syncCallService(url);
	}
	return CMS.RemovalWayList;
};
//免职原因
CMS.RemovalCauseList = null;
CMS.getRemovalCauseList = function(){
	if(CMS.RemovalCauseList==null){
		var url = 'dictPosition/queryComboboxRemovalCause.do';
		CMS.RemovalCauseList = syncCallService(url);
	}
	return CMS.RemovalCauseList;
};
//任职方式
CMS.OfficeWayList = null;
CMS.getOfficeWayList = function(){
	if(CMS.OfficeWayList==null){
		var url = 'dictPosition/queryComboboxOfficeWay.do';
		CMS.OfficeWayList = syncCallService(url);
	}
	return CMS.OfficeWayList;
};
//部门
CMS.SchoolDeptList = null;
CMS.getSchoolDeptList = function(){
	if(CMS.SchoolDeptList==null){
		var url = 'dictInstitutions/queryComboboxSchoolDept.do';
		CMS.SchoolDeptList = syncCallService(url);
	}
	return CMS.SchoolDeptList;
};
//开启稿件管理的部门
CMS.OpenScriptSchoolDeptList = null;
CMS.getOpenScriptSchoolDeptList = function(){
	if(CMS.OpenScriptSchoolDeptList==null){
		var url = 'dictInstitutions/queryOpenScriptComboboxSchoolDept.do';
		CMS.OpenScriptSchoolDeptList = syncCallService(url);
	}
	return CMS.OpenScriptSchoolDeptList;
};
//依托平台
CMS.SupportPlatformList = null;
CMS.getSupportPlatformList = function(){
	if(CMS.SupportPlatformList==null){
		var url = 'dictResearch/queryComboboxSupportPlatform.do';
		CMS.SupportPlatformList = syncCallService(url);
	}
	return CMS.SupportPlatformList;
};
//项目类别
CMS.ProjectTypeList = null;
CMS.getProjectTypeList = function(){
	if(CMS.ProjectTypeList==null){
		var url = 'dictResearch/queryComboboxProjectType.do';
		CMS.ProjectTypeList = syncCallService(url);
	}
	return CMS.ProjectTypeList;
};
//国民经济
CMS.NationalEconomyList = null;
CMS.getNationalEconomyList = function(){
	if(CMS.NationalEconomyList==null){
		var url = 'dictResearch/queryComboboxNationalEconomy.do';
		CMS.NationalEconomyList = syncCallService(url);
	}
	return CMS.NationalEconomyList;
};
//社会经济
CMS.CommunityEconomyList = null;
CMS.getCommunityEconomyList = function(){
	if(CMS.CommunityEconomyList==null){
		var url = 'dictResearch/queryComboboxCommunityEconomy.do';
		CMS.CommunityEconomyList = syncCallService(url);
	}
	return CMS.CommunityEconomyList;
};
//项目合作方式
CMS.ProjectCoopModeList = null;
CMS.getProjectCoopModeList = function(){
	if(CMS.ProjectCoopModeList==null){
		var url = 'dictResearch/queryComboboxProjectCoopMode.do';
		CMS.ProjectCoopModeList = syncCallService(url);
	}
	return CMS.ProjectCoopModeList;
};
//项目经费来源
CMS.ProjectMoneySourceList = null;
CMS.getProjectMoneySourceList = function(){
	if(CMS.ProjectMoneySourceList==null){
		var url = 'dictResearch/queryComboboxProjectMoneySource.do';
		CMS.ProjectMoneySourceList = syncCallService(url);
	}
	return CMS.ProjectMoneySourceList;
};
//学科大类
CMS.DisciplineList = null;
CMS.getDisciplineList = function(){
	if(CMS.DisciplineList==null){
		var url = 'dictSubject/queryComboboxDiscipline.do';
		CMS.DisciplineList = syncCallService(url);
	}
	return CMS.DisciplineList;
};
//五大学科
CMS.FiveBigSubjectList = null;
CMS.getFiveBigSubjectList = function(){
	if(CMS.FiveBigSubjectList==null){
		var url = 'dictSubject/queryComboboxFiveBigSubject.do';
		CMS.FiveBigSubjectList = syncCallService(url);
	}
	return CMS.FiveBigSubjectList;
};
//一级学科
CMS.Level1DisciplineList = null;
CMS.getLevel1DisciplineList = function(){
	if(CMS.Level1DisciplineList==null){
		var url = 'dictSubject/queryComboboxLevel1.do';
		CMS.Level1DisciplineList = syncCallService(url);
	}
	return CMS.Level1DisciplineList;
};
//二级学科
CMS.Level2DisciplineList = null;
CMS.getLevel2DisciplineList = function(){
	if(CMS.Level2DisciplineList==null){
		var url = 'dictSubject/queryComboboxLevel2.do';
		CMS.Level2DisciplineList = syncCallService(url);
	}
	return CMS.Level2DisciplineList;
};
//学院的二级学科
CMS.SchoolMajorList = null;
CMS.getSchoolMajorList = function(){
	if(CMS.Level2DisciplineList==null){
		var url = 'dictSubject/querySchoolDeptCombogridLevel2.do';
		CMS.SchoolMajorList = syncCallService(url);
	}
	return CMS.SchoolMajorList;
};
//职称
CMS.TitleList = null;
CMS.getTitleList = function(){
	if(CMS.TitleList==null){
		var url = 'dictTitle/queryComboboxTitle.do';
		CMS.TitleList = syncCallService(url);
	}
	return CMS.TitleList;
};
//专业级别
CMS.ProfessionalLevelList = null;
CMS.getProfessionalLevelList = function(){
	if(CMS.ProfessionalLevelList==null){
		var url = 'dictTitle/queryComboboxProfessional.do';
		CMS.ProfessionalLevelList = syncCallService(url);
	}
	return CMS.ProfessionalLevelList;
};
//专业技术等级
CMS.TitleLevelList = null;
CMS.getTitleLevelList = function(){
	if(CMS.TitleLevelList==null){
		var url = 'dictTitle/queryComboboxTitleLevel.do';
		CMS.TitleLevelList = syncCallService(url);
	}
	return CMS.TitleLevelList;
};
//取得资格途径
CMS.QualifiedWayList = null;
CMS.getQualifiedWayList = function(){
	if(CMS.QualifiedWayList==null){
		var url = 'dictTitle/queryComboboxQualifiedWay.do';
		CMS.QualifiedWayList = syncCallService(url);
	}
	return CMS.QualifiedWayList;
};
//岗位聘用类型
CMS.PostEmployTypeList = null;
CMS.getPostEmployTypeList = function(){
	if(CMS.PostEmployTypeList==null){
		var url = 'dictTitle/queryComboboxPostEmployType.do';
		CMS.PostEmployTypeList = syncCallService(url);
	}
	return CMS.PostEmployTypeList;
};
//聘任情况
CMS.EmploySituationList = null;
CMS.getEmploySituationList = function(){
	if(CMS.EmploySituationList==null){
		var url = 'dictTitle/queryComboboxEmploySituation.do';
		CMS.EmploySituationList = syncCallService(url);
	}
	return CMS.EmploySituationList;
};
//项目现状
CMS.ProjectStatusList = null;
CMS.getProjectStatusList = function(){
	if(CMS.ProjectStatusList==null){
		var url = 'dictResearch/queryComboboxProjectStatus.do';
		CMS.ProjectStatusList = syncCallService(url);
	}
	return CMS.ProjectStatusList;
};
//项目来源
CMS.ProjectSourceList = null;
CMS.getProjectSourceList = function(){
	if(CMS.ProjectSourceList==null){
		var url = 'dictResearch/queryComboboxProjectSource.do';
		CMS.ProjectSourceList = syncCallService(url);
	}
	return CMS.ProjectSourceList;
};
//项目级别
CMS.ProjectLevelList = null;
CMS.getProjectLevelList = function(){
	if(CMS.ProjectLevelList==null){
		var url = 'dictResearch/queryComboboxProjectLevel.do';
		CMS.ProjectLevelList = syncCallService(url);
	}
	return CMS.ProjectLevelList;
};
//任务来源
CMS.TaskSourceList = null;
CMS.getTaskSourceList = function(){
	if(CMS.TaskSourceList==null){
		var url = 'dictResearch/queryComboboxTaskSource.do';
		CMS.TaskSourceList = syncCallService(url);
	}
	return CMS.TaskSourceList;
};
//著作类别
CMS.BookTypeList = null;
CMS.getBookTypeList = function(){
	if(CMS.BookTypeList==null){
		var url = 'dictResearch/queryComboboxBookType.do';
		CMS.BookTypeList = syncCallService(url);
	}
	return CMS.BookTypeList;
};
//著作级别
CMS.BookLevelList = null;
CMS.getBookLevelList = function(){
	if(CMS.BookLevelList==null){
		var url = 'dictResearch/queryComboboxBookLevel.do';
		CMS.BookLevelList = syncCallService(url);
	}
	return CMS.BookLevelList;
};
//论文类别
CMS.PaperTypeList = null;
CMS.getPaperTypeList = function(){
	if(CMS.PaperTypeList==null){
		var url = 'dictResearch/queryComboboxPaperType.do';
		CMS.PaperTypeList = syncCallService(url);
	}
	return CMS.PaperTypeList;
};
//论文奖励类别
CMS.PaperAwardTypeList = null;
CMS.getPaperAwardTypeList = function(){
	if(CMS.PaperAwardTypeList==null){
		var url = 'dictResearch/queryComboboxPaperAwardType.do';
		CMS.PaperAwardTypeList = syncCallService(url);
	}
	return CMS.PaperAwardTypeList;
};
//学位
CMS.DegreeList = null;
CMS.getDegreeList = function(){
	if(CMS.DegreeList==null){
		var url = 'dictEduDegree/queryComboboxDegree.do';
		CMS.DegreeList = syncCallService(url);
	}
	return CMS.DegreeList;
};
//学历
CMS.EducationList = null;
CMS.getEducationList = function(){
	if(CMS.EducationList==null){
		var url = 'dictEduDegree/queryComboboxEducation.do';
		CMS.EducationList = syncCallService(url);
	}
	return CMS.EducationList;
};
//学习方式
CMS.StudyTypeList = null;
CMS.getStudyTypeList = function(){
	if(CMS.StudyTypeList==null){
		var url = 'dictEduDegree/queryComboboxStudyType.do';
		CMS.StudyTypeList = syncCallService(url);
	}
	return CMS.StudyTypeList;
};
//学习形式
CMS.StudyWayList = null;
CMS.getStudyWayList = function(){
	if(CMS.StudyWayList==null){
		var url = 'dictEduDegree/queryComboboxStudyWay.do';
		CMS.StudyWayList = syncCallService(url);
	}
	return CMS.StudyWayList;
};
//管理等级
CMS.ManageLevelList = null;
CMS.getManageLevelList = function(){
	if(CMS.ManageLevelList==null){
		var url = 'dictPosition/queryComboboxManageLevel.do';
		CMS.ManageLevelList = syncCallService(url);
	}
	return CMS.ManageLevelList;
};
//离退后享受级别
CMS.RetiredTreatmentList = null;
CMS.getRetiredTreatmentList = function(){
	if(CMS.RetiredTreatmentList==null){
		var url = 'dictWorkPost/queryComboboxRetiredTreatment.do';
		CMS.RetiredTreatmentList = syncCallService(url);
	}
	return CMS.RetiredTreatmentList;
};
//出版资助
CMS.PublishSupportList = null;
CMS.getPublishSupportList = function(){
	if(CMS.PublishSupportList==null){
		var url = 'dictResearch/queryComboboxPublishSupport.do';
		CMS.PublishSupportList = syncCallService(url);
	}
	return CMS.PublishSupportList;
};
//出版单位
CMS.PublishDeptList = null;
CMS.getPublishDeptList = function(){
	if(CMS.PublishDeptList==null){
		var url = 'dictResearch/queryComboboxPublishDept.do';
		CMS.PublishDeptList = syncCallService(url);
	}
	return CMS.PublishDeptList;
};
//鉴定类别
CMS.AppraisalCategoryList = null;
CMS.getAppraisalCategoryList = function(){
	if(CMS.AppraisalCategoryList==null){
		var url = 'dictResearch/queryComboboxAppraisalCategory.do';
		CMS.AppraisalCategoryList = syncCallService(url);
	}
	return CMS.AppraisalCategoryList;
};
//鉴定类型
CMS.AppraisalTypeList = null;
CMS.getAppraisalTypeList = function(){
	if(CMS.AppraisalTypeList==null){
		var url = 'dictResearch/queryComboboxAppraisalType.do';
		CMS.AppraisalTypeList = syncCallService(url);
	}
	return CMS.AppraisalTypeList;
};
//研究类别
CMS.ResearchTypeList = null;
CMS.getResearchTypeList = function(){
	if(CMS.ResearchTypeList==null){
		var url = 'dictResearch/queryComboboxResearchType.do';
		CMS.ResearchTypeList = syncCallService(url);
	}
	return CMS.ResearchTypeList;
};
//省份
CMS.ProvinceList = null;
CMS.getProvinceList = function(){
	if(CMS.ProvinceList==null){
		var url = 'dictBase/queryComboboxProvince.do';
		CMS.ProvinceList = syncCallService(url);
	}
	return CMS.ProvinceList;
};
//任课状况
CMS.TeachingStatusList = null;
CMS.getTeachingStatusList = function(){
	if(CMS.TeachingStatusList==null){
		var url = 'dictWorkPost/queryComboboxTeachingStatus.do';
		CMS.TeachingStatusList = syncCallService(url);
	}
	return CMS.TeachingStatusList;
};
//系别
CMS.DepartmentList = null;
CMS.getDepartmentList = function(){
	if(CMS.DepartmentList==null){
		var url='dictInstitutions/queryComboboxDepartment.do';
		CMS.DepartmentList = syncCallService(url);
	}
	return CMS.DepartmentList;
};
//教研室
CMS.getResearchUnitList = function(departmentId){
	var url='dictInstitutions/queryComboboxResearchUnit.do';
	var content = {'department.departmentId':departmentId};
	return syncCallService(url,content);
};
//系别
CMS.ExecutiveClassList = null;
CMS.getExecutiveClassList = function(){
	if(CMS.ExecutiveClassList==null){
		var url='dictInstitutions/queryComboboxExecutiveClass.do';
		CMS.ExecutiveClassList = syncCallService(url);
	}
	return CMS.ExecutiveClassList;
};
//文章分类
CMS.ArchiveTypeList = null;
CMS.getArchiveTypeList = function(){
	if(CMS.ArchiveTypeList==null){
		var url = 'cmsAdmin/queryComboboxArchiveType.do';
		CMS.ArchiveTypeList = syncCallService(url);
	}
	return CMS.ArchiveTypeList;
};
//培养方向
CMS.TrainingDirectionList = null;
CMS.getTrainingDirectionList = function(){
	if(CMS.TrainingDirectionList==null){
		var url = 'programs/queryComboboxTrainingDirection.do';
		CMS.TrainingDirectionList = syncCallService(url);
	}
	return CMS.TrainingDirectionList;
};
//课程类型
CMS.CourseTypeList = null;
CMS.getCourseTypeList = function(){
	if(CMS.CourseTypeList==null){
		var url = 'dictCourse/queryComboboxCourseType.do';
		CMS.CourseTypeList = syncCallService(url);
	}
	return CMS.CourseTypeList;
};
//学科门类
CMS.SubjectCatList = null;
CMS.getSubjectCatList = function(){
	if(CMS.SubjectCatList==null){
		var url = 'dictSubject/queryComboboxSubjectCat.do';
		CMS.SubjectCatList = syncCallService(url);
	}
	return CMS.SubjectCatList;
};
//专业
CMS.MajorList = null;
CMS.getMajorList = function(){
	if(CMS.MajorList==null){
		var url = 'dictSubject/queryComboboxMajor.do';
		CMS.MajorList = syncCallService(url);
	}
	return CMS.MajorList;
};
//学年
CMS.SchoolYearList = null;
CMS.getSchoolYearList = function(){
	if(CMS.SchoolYearList==null){
		var url = 'dictTime/queryComboboxSchoolYear.do';
		CMS.SchoolYearList = syncCallService(url);
	}
	return CMS.SchoolYearList;
};
//学期
CMS.SemesterList = null;
CMS.getSemesterList = function(){
	if(CMS.SemesterList==null){
		var url = 'dictTime/queryComboboxSemester.do';
		CMS.SemesterList = syncCallService(url);
	}
	return CMS.SemesterList;
};
//培养方向
CMS.TrainingDirectionList = null;
CMS.getTrainingDirectionList = function(){
	if(CMS.TrainingDirectionList==null){
		var url = 'programs/queryComboboxTrainingDirection.do';
		CMS.TrainingDirectionList = syncCallService(url);
	}
	return CMS.TrainingDirectionList;
};
//对外网站参数
CMS.CMSOptions = null;
CMS.getCMSOptions = function(){
	if(CMS.CMSOptions==null){
		var url = 'common/getCMSOptionsCommon.do';
		CMS.CMSOptions = syncCallService(url);
	}
	return CMS.CMSOptions;
};
//相对学期
CMS.RelativeSemesterList = null;
CMS.getRelativeSemesterList = function(){
	if(CMS.FieldTypeList==null){
		var url = 'js/dictJson/combobox_relativeSemester.json?time='+new Date();
		CMS.RelativeSemesterList = syncCallService(url);
	}
	return CMS.RelativeSemesterList;
};
//自定义表单字段类型
CMS.FieldTypeList = null;
CMS.getFieldTypeList = function(){
	if(CMS.FieldTypeList==null){
		var url = 'js/dictJson/fieldType.json?time='+new Date();
		CMS.FieldTypeList = syncCallService(url);
	}
	return CMS.FieldTypeList;
};
//自定义表单字段组件类型
CMS.FieldInputTypeList = null;
CMS.getFieldInputTypeList = function(){
	if(CMS.FieldInputTypeList==null){
		var url = 'js/dictJson/fieldInputType.json?time='+new Date();
		$.ajax({  
            type:  "post",  
            url:  url,  
            dataType:  "json",  
            async : false,
			cache : false,
            success:  function (data) {  
            	CMS.FieldInputTypeList =data; 
            },  
            error:  function () { 
            	alert("网络连接失败，请重新加载本页面。。。!");
            }  
		});
	}
	return CMS.FieldInputTypeList;
};
//外聘教师类型
CMS.ExTeaTypeList = null;
CMS.getExTeaTypeList = function(){
	if(CMS.ExTeaTypeList==null){
		var url = 'dictInstitutions/queryComboboxExTeaType.do';
		CMS.ExTeaTypeList = syncCallService(url);
	}
	return CMS.ExTeaTypeList;
};
//政治面貌异常类别
CMS.PoliticalStatusAbnormalList = null;
CMS.getPoliticalStatusAbnormalList = function(){
	if(CMS.PoliticalStatusAbnormalList==null){
		var url = 'dictBase/queryComboboxPoliticalStatusAbnormal.do';
		CMS.PoliticalStatusAbnormalList = syncCallService(url);
	}
	return CMS.PoliticalStatusAbnormalList;
};
//导师类别
CMS.MentorTypeList = null;
CMS.getMentorTypeList = function(){
	if(CMS.MentorTypeList==null){
		var url = 'dictWorkPost/queryComboboxMentorType.do';
		CMS.MentorTypeList = syncCallService(url);
	}
	return CMS.MentorTypeList;
};
//软件著作类型
CMS.SoftwareTypeList = null;
CMS.getSoftwareTypeList = function(){
	if(CMS.SoftwareTypeList==null){
		var url = 'dictResearch/queryComboboxSoftwareType.do';
		CMS.SoftwareTypeList = syncCallService(url);
	}
	return CMS.SoftwareTypeList;
};
